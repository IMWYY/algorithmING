#include "list.h"

ListNode* removeNthFromEnd(ListNode* head, int n) {
  ListNode dummy(-1, head);
  ListNode *slow = &dummy, *fast = &dummy;
  while (n > 0 && fast != nullptr) {
    fast = fast->next;
    n--;
  }
  if (n > 0) return head;
  while (fast->next != nullptr) {
    slow = slow->next;
    fast = fast->next;
  }
  slow->next = slow->next->next;
  return dummy.next;
}