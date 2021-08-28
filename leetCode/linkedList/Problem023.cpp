#include "list.h"

ListNode* mergeKLists(vector<ListNode*>& lists) {
  auto f = [](const ListNode* p1, const ListNode* p2) {
    return p1->val > p2->val;
  };
  std::priority_queue<ListNode*, std::vector<ListNode*>, decltype(f)> pq(f);
  for (ListNode* n : lists) {
    if (n) pq.push(n);
  }

  ListNode dummy;
  ListNode* cur = &dummy;
  while (!pq.empty()) {
    ListNode* p = pq.top();
    pq.pop();
    cur->next = p;
    if (p->next) pq.push(p->next);
    cur = cur->next;
  }
  return dummy.next;
}