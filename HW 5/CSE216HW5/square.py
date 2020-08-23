# Nitin Dev
# NDEV
# 112298641


from rectangle import Rectangle
from quadrilateral import Quadrilateral
import math


class Square(Rectangle):

    def __init__(self, *floats) -> None:
        super().__init__(*floats)
        if not self.__is_member():
            raise TypeError("A square cannot be formed by the given coordinates.")


    def __is_member(self) -> bool:
        return (Square.isValidSquare(self.vertices[0].x, self.vertices[0].y, self.vertices[1].x, self.vertices[1].y, self.vertices[2].x, self.vertices[2].y, self.vertices[3].x, self.vertices[3].y)) and (self.area() != 0)



    @staticmethod
    def isValidSquare(x1, y1, x2, y2, x3, y3, x4, y4) -> bool:
        side1 = math.sqrt(math.pow((x1 - x2), 2) + math.pow((y1 - y2), 2))
        side2 = math.sqrt(math.pow((x2 - x3), 2) + math.pow((y2 - y3), 2))
        side3 = math.sqrt(math.pow((x3 - x4), 2) + math.pow((y3 - y4), 2))
        side4 = math.sqrt(math.pow((x4 - x1), 2) + math.pow((y4 - y1), 2))

        return (side1 == side2) and (side2 == side3) and (side3 == side4) and (side4 == side1)



    def snap(self) -> Quadrilateral:
        """Snaps the sides of the square such that each corner (x,y) is modified to be a corner (x',y') where x' is the
        integer value closest to x and y' is the integer value closest to y. This, of course, may change the shape to a
        general quadrilateral, hence the return type. The only exception is when the square is positioned in a way where
        this approximation will lead it to vanish into a single point. In that case, a call to snap() will not modify
        this square in any way."""

        tempVert = self.vertices

        v1 = (math.floor(tempVert[0].x + 0.5), math.floor(tempVert[0].y + 0.5))
        v2 = (math.floor(tempVert[1].x + 0.5), math.floor(tempVert[1].y + 0.5))
        v3 = (math.floor(tempVert[2].x + 0.5), math.floor(tempVert[2].y + 0.5))
        v4 = (math.floor(tempVert[3].x + 0.5), math.floor(tempVert[3].y + 0.5))

        if (v1 == v2) and (v2 == v3) and (v3 == v4) and (v4 == v1):
            return Quadrilateral(tempVert[0].x, tempVert[0].y, tempVert[1].x, tempVert[1].y, tempVert[2].x, tempVert[2].y, tempVert[3].x, tempVert[3].y)
        else:
            return Quadrilateral(math.floor(tempVert[0].x + 0.5), math.floor(tempVert[0].y + 0.5), math.floor(tempVert[1].x + 0.5), math.floor(tempVert[1].y + 0.5), math.floor(tempVert[2].x + 0.5), math.floor(tempVert[2].y + 0.5), math.floor(tempVert[3].x + 0.5), math.floor(tempVert[3].y + 0.5))
