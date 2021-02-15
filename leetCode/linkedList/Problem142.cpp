#include <list.h>

/**
 * slow and fast pointer to detect cycle
 */
ListNode* detectCycle(ListNode* head) {
  if (head == nullptr || head->next == nullptr) return nullptr;

  ListNode* firstp = head;
  ListNode* secondp = head;
  bool isCycle = false;

  while (firstp != nullptr && secondp != nullptr) {
    firstp = firstp->next;
    if (secondp->next == nullptr) return nullptr;
    secondp = secondp->next->next;
    if (firstp == secondp) {
      isCycle = true;
      break;
    }
  }

  if (!isCycle) return nullptr;
  firstp = head;
  while (firstp != secondp) {
    firstp = firstp->next;
    secondp = secondp->next;
  }

  return firstp;
}