#include <list.h>

ListNode* oddEvenList(ListNode* head) {
  if (!head) return head;
  ListNode *odd = head, *evenhead = head->next, *even = evenhead;
  while (even && even->next) {
    odd->next = odd->next->next; // odd nodes will not be nullptr
    even->next = even->next->next;
    odd = odd->next;
    even = even->next;
  }
  odd->next = evenhead;
  return head;
}