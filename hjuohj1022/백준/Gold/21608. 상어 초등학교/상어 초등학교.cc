#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

int N;
int map[21][21]; // 교실 격자 (1~20 사용)
vector<int> students_order; // 자리 앉는 순서
vector<int> likes[401]; // 각 학생이 좋아하는 4명 저장 (최대 400번 학생)

int dr[] = { -1, 1, 0, 0 };
int dc[] = { 0, 0, -1, 1 };

int score_table[] = { 0, 1, 10, 100, 1000 };

void input() {
  cin >> N;
  for (int i = 0; i < N * N; i++) {
    int student;
    cin >> student;
    students_order.push_back(student);
    for (int j = 0; j < 4; j++) {
      int like;
      cin >> like;
      likes[student].push_back(like);
    }
  }
}

void solve() {
  // 1. 모든 학생을 순서대로 배치
  for (int student : students_order) {

    // 현재 학생에게 가장 좋은 자리를 찾기 위한 변수들
    int best_r = 0, best_c = 0;
    int max_like = -1;
    int max_empty = -1;

    // 교실의 모든 칸을 순회 (행 1->N, 열 1->N 순서)
    // 이 순서대로 돌면 '행 번호 작은 순 -> 열 번호 작은 순' 조건 자동 만족
    for (int r = 1; r <= N; r++) {
      for (int c = 1; c <= N; c++) {
        if (map[r][c] != 0) continue; // 이미 누가 앉은 자리는 패스

        int curr_like = 0;
        int curr_empty = 0;

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
          int nr = r + dr[i];
          int nc = c + dc[i];

          // 격자 범위 체크
          if (nr < 1 || nr > N || nc < 1 || nc > N) continue;

          // 인접한 칸 확인
          if (map[nr][nc] == 0) {
            curr_empty++;
          }
          else {
            // 앉아있는 학생이 내가 좋아하는 학생인지 확인
            for (int target : likes[student]) {
              if (map[nr][nc] == target) {
                curr_like++;
                break;
              }
            }
          }
        }

        // [조건 비교 로직]
        // 1. 좋아하는 학생이 더 많으면 무조건 갱신
        if (curr_like > max_like) {
          max_like = curr_like;
          max_empty = curr_empty;
          best_r = r;
          best_c = c;
        }
        // 2. 좋아하는 학생 수는 같은데, 빈 칸이 더 많으면 갱신
        else if (curr_like == max_like && curr_empty > max_empty) {
          max_empty = curr_empty;
          best_r = r;
          best_c = c;
        }
        // 3, 4번 조건(행/열 번호 최소)은 for문 순서 덕분에
        // '초과(>)'일 때만 갱신하면 자동으로 가장 앞선 좌표가 유지됨
      }
    }
    // 결정된 자리에 학생 앉히기
    map[best_r][best_c] = student;
  }

  // 2. 만족도 계산
  int total_score = 0;
  for (int r = 1; r <= N; r++) {
    for (int c = 1; c <= N; c++) {
      int student = map[r][c];
      int like_cnt = 0;

      for (int i = 0; i < 4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];

        if (nr < 1 || nr > N || nc < 1 || nc > N) continue;

        for (int target : likes[student]) {
          if (map[nr][nc] == target) {
            like_cnt++;
            break;
          }
        }
      }
      total_score += score_table[like_cnt];
    }
  }

  cout << total_score << endl;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  input();
  solve();

  return 0;
}