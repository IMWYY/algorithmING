#include "list.h"

bool isPalindrome(ListNode *head) {
  if (!head || !head->next) return true;
  ListNode *slow = head, *fast = head;
  while (fast) {
    slow = slow->next;
    fast = fast->next;
    if (fast) fast = fast->next;
  }

  ListNode *cur = slow, *prev = nullptr;
  while (cur) {
    ListNode *tmp = cur->next;
    cur->next = prev;
    prev = cur;
    cur = tmp;
  }

  ListNode *p = prev, *q = head;
  while (p && q) {
    if (p->val != q->val) return false;
    p = p->next;
    q = q->next;
  }
  return true;
}