#include <algorithm>

#include "list.h"

/**
 * solution 1:
 * find the length of list, then start traverse from the point where
 * the length to the end is the same
 * O(n) time + O(1) space
 */
ListNode *getIntersectionNode1(ListNode *headA, ListNode *headB) {
  int lena = 0, lenb = 0;
  ListNode *pa = headA, *pb = headB;
  while (pa) {
    pa = pa->next;
    lena++;
  }
  while (pb) {
    pb = pb->next;
    lenb++;
  }

  if (lena < lenb) {
    std::swap(lena, lenb);
    std::swap(headA, headB);
  }

  pa = headA;
  pb = headB;
  for (int i = 0; i < lena - lenb; ++i) {
    pa = pa->next;
  }

  while (pa) {
    if (pa == pb) return pa;
    pa = pa->next;
    pb = pb->next;
  }
  return nullptr;
}

/**
 * two pointer, pointer 1 traverse headA then headB,
 * pointer 2 traverse headB then headA.
 * O(n) time + O(1) space
 */
ListNode *getIntersectionNode2(ListNode *headA, ListNode *headB) {
  ListNode *pa = headA, *pb = headB;
  // bool changea = false, changeb = false;
  //   while (pa && pb) {
  //     if (pa == pb) return pa;
  //     pa = pa->next;
  //     if (!pa && !changea) {
  //       pa = headB;
  //       changea = true;
  //     }

  //     pb = pb->next;
  //     if (!pb && !changeb) {
  //       pb = headA;
  //       changeb = true;
  //     }
  //   }
  while (pa != pb) {
    pa = pa->next;
    pb = pb->next;
    if (pa == pb) return pa; // note: handle nullptr case here
    if (!pa) pa = headB;
    if (!pb) pb = headA;
  }
  return pa;
}