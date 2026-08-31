class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstCritIndex = -1;
        int prevCritIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1;

        while (curr.next != null) {
            // Check if curr is a local maximum or local minimum
            boolean isLocalMax = curr.val > prev.val && curr.val > curr.next.val;
            boolean isLocalMin = curr.val < prev.val && curr.val < curr.next.val;

            if (isLocalMax || isLocalMin) {
                if (firstCritIndex == -1) {
                    firstCritIndex = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - prevCritIndex);
                }
                prevCritIndex = currentIndex;
            }

            prev = curr;
            curr = curr.next;
            currentIndex++;
        }

        // Less than 2 critical points found
        if (firstCritIndex == -1 || firstCritIndex == prevCritIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCritIndex - firstCritIndex;
        return new int[]{minDistance, maxDistance};
    }
}
