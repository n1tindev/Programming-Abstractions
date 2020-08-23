# Nitin Dev
# NDEV
# 112298641

from quadrilateral import Quadrilateral
from two_d_point import TwoDPoint
import math


class Rectangle(Quadrilateral):

    def __init__(self, *floats) -> None:
        super().__init__(*floats)
        if not self.__is_member():
            raise TypeError("A rectangle cannot be formed by the given coordinates.")

    def __is_member(self) -> bool:
        """Returns True if the given coordinates form a valid rectangle, and False otherwise."""
        return (Rectangle.isValidRectangle(self.vertices[0].x, self.vertices[0].y, self.vertices[1].x, self.vertices[1].y, self.vertices[2].x, self.vertices[2].y, self.vertices[3].x, self.vertices[3].y)) and (self.area() != 0)

    def center(self) -> TwoDPoint:
        """Returns the center of this rectangle, calculated to be the point of intersection of its diagonals."""
        val = self.vertices
        return TwoDPoint(((val[0].x + val[2].x) / 2), ((val[0].x + val[2].x) / 2))

    def area(self) -> float:
        """Returns the area of this rectangle. The implementation invokes the side_lengths() method from the superclass,
        and computes the product of this rectangle's length and width."""
        sideLen = super().side_lengths()
        return sideLen[0] * sideLen[1]

    @staticmethod
    def isValidRectangle(x1, y1, x2, y2, x3, y3, x4, y4) -> bool:          # True -> if valid rectangle

        side1 = math.sqrt(math.pow((x1 - x2), 2) + math.pow((y1 - y2), 2))
        side2 = math.sqrt(math.pow((x2 - x3), 2) + math.pow((y2 - y3), 2))
        side3 = math.sqrt(math.pow((x3 - x4), 2) + math.pow((y3 - y4), 2))
        side4 = math.sqrt(math.pow((x4 - x1), 2) + math.pow((y4 - y1), 2))

        diagonal1 = math.sqrt(math.pow((x1 - x3), 2) + math.pow((y1 - y3), 2))
        diagonal2 = math.sqrt(math.pow((x2 - x4), 2) + math.pow((y2 - y4), 2))

        return (side1 == side3) and (side2 == side4) and (diagonal1 == diagonal2)
