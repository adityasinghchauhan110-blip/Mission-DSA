class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the point on/inside the rectangle closest to the circle's center
        int nearestX = Math.max(x1, Math.min(xCenter, x2));
        int nearestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Calculate squared distance from circle center to nearest point
        int dx = xCenter - nearestX;
        int dy = yCenter - nearestY;
        
        // Check if the distance is within the radius
        return (dx * dx + dy * dy) <= radius * radius;
    }
}
