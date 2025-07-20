#include <iostream>
#include <sstream>
#include <vector>
#include <string>

using namespace std;

int n;
vector<int> ips;
int ip_and, ip_or, diff, mask, network;
int commonBits;


int ipToInt(const string& ipStr) {
  stringstream ss(ipStr);
  string octet;
  int ip = 0;
  for (int i = 0; i < 4; ++i) {
    getline(ss, octet, '.');
    ip = (ip << 8) | stoi(octet);
  }
  return ip;
}

string intToIp(int ip) {
  return to_string((ip >> 24) & 0xFF) + "." +
    to_string((ip >> 16) & 0xFF) + "." +
    to_string((ip >> 8) & 0xFF) + "." +
    to_string(ip & 0xFF);
}

void calcNetworkInfo() {
  ip_and = ips[0];
  ip_or = ips[0];
  for (int i = 1; i < n; ++i) {
    ip_and &= ips[i];
    ip_or |= ips[i];
  }
  diff = ip_and ^ ip_or;

  commonBits = 0;
  for (int i = 31; i >= 0; --i) {
    if (diff & (1 << i)) break;
    commonBits++;
  }
  mask = (commonBits == 0) ? 0 : (~0 << (32 - commonBits));
  network = ips[0] & mask;
}

int main() {
  cin >> n;
  ips.resize(n);
  for (int i = 0; i < n; ++i) {
    string s;
    cin >> s;
    ips[i] = ipToInt(s);
  }
  calcNetworkInfo();
  cout << intToIp(network) << endl;
  cout << intToIp(mask) << endl;
}
