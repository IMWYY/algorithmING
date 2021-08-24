#include "list.h"

ListNode* reverseList(ListNode* head) {
  if (!head || !head->next) return head;
  ListNode *prev = head, *cur = head->next;
  prev->next = nullptr;
  while (cur) {
    ListNode* tmp = cur->next;
    cur->next = prev;
    prev = cur;
    cur = tmp;
  }
  return prev;
}