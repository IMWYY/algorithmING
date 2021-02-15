#include <list.h>

/**
 * slow and fast pointers
 * O(n) time + O(1) space
 */
bool hasCycle(ListNode* head) {
  if (head == nullptr || head->next == nullptr) return false;
  ListNode *slow = head, *fast = head;
  while (fast != nullptr) {
    slow = slow->next;
    fast = fast->next;
    if (fast != nullptr) fast = fast->next;
    if (fast == slow) return true;
  }
  return false;
}
