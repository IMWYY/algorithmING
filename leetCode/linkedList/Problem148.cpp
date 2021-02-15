#include <list.h>

/***
 * solution 1: merge sort
 */
ListNode* mergeList(ListNode*, ListNode*);
ListNode* sortList1(ListNode* head) {
  if (head == nullptr || head->next == nullptr) return head;
  ListNode *slow = head, *fast = head, *pre = nullptr;
  while (fast != nullptr && fast->next != nullptr) {
    pre = slow;
    slow = slow->next;
    fast = fast->next->next;
  }
  pre->next = nullptr;  // break the list into two
  ListNode* h1 = sortList1(head);
  ListNode* h2 = sortList1(slow);
  return mergeList(h1, h2);
}

ListNode* mergeList(ListNode* h1, ListNode* h2) {
  ListNode dummy;
  ListNode* cur = &dummy;
  while (h1 != nullptr && h2 != nullptr) {
    if (h1->val < h2->val) {
      cur->next = h1;
      h1 = h1->next;
    } else {
      cur->next = h2;
      h2 = h2->next;
    }
    cur = cur->next;
  }
  if (h1 != nullptr) cur->next = h1;
  if (h2 != nullptr) cur->next = h2;
  return dummy.next;
}

/***
 * solution2: quick sort
 */
ListNode* quicksort(ListNode*, ListNode*);
ListNode* sortList2(ListNode* head) { return quicksort(head, nullptr); }

ListNode* quicksort(ListNode* head, ListNode* tail) {
  if (head == nullptr || head->next == nullptr || head == tail ||
      head->next == tail)
    return head;
  ListNode* p = head;
  ListNode* q = head->next;

  while (q != tail) {
    if (q->val < head->val) {
      p = p->next;
      int tmp = q->val;
      q->val = p->val;
      p->val = tmp;
    }
    q = q->next;
  }
  int tmp = p->val;
  p->val = head->val;
  head->val = tmp;
  quicksort(head, p);
  quicksort(p->next, tail);
  return head;
}
