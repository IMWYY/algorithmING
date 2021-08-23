#include "list.h"
#include <vector>

std::vector<ListNode*> reverseK(ListNode* head, int k) {
    ListNode* prev = nullptr, *cur = head;
    ListNode* tail = head;
    int idx = 0;
    while (idx++ < k && cur) {
        ListNode* tmp = cur->next;
        cur->next = prev;
        prev = cur;
        cur = tmp;
    }
    return {prev, tail, cur};
}

ListNode* reverseKGroup(ListNode* head, int k) {
    if (k == 1) return head;

    ListNode* p = head, *new_head = nullptr;

    int len = 0, idx = 0;
    while (p) {
        len ++;
        p = p->next;
    }
    p = head;

    std::vector<ListNode*> prev = {};

    while (p) {
        std::vector<ListNode*> res;
        if (len - idx < k) {
            res = {p, nullptr, nullptr};
        } else {
            res = reverseK(p, k);
        }
        if (!prev.empty()) {
            prev[1]->next = res[0];
        } else {
            new_head = res[0];
        }
        prev = res;
        p = res[2];
        idx += k;
    }
    return new_head;
}