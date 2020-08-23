# Nitin Dev
# NDEV
# 112298641

from two_d_point import TwoDPoint
from typing import Tuple
import math


class Quadrilateral:

    def __init__(self, *floats) -> None:        # added -> NONE
        points = TwoDPoint.from_coordinates(list(floats))
        self.__vertices = tuple(points[0:4])    # bug fixed from 0:3 to 0:4

    @property
    def vertices(self) -> Tuple[TwoDPoint]:     # added -> Tuple[TwoDPoint]
        return self.__vertices

    def side_lengths(self) -> Tuple[float]:     # added -> Tuple[float]
        """Returns a tuple of four floats, each denoting the length of a side of this quadrilateral. The value must be
        ordered clockwise, starting from the top left corner."""
        side0 = math.sqrt(math.pow(self.__vertices[0].x - self.vertices[1].x, 2) + math.pow(self.__vertices[0].y - self.vertices[1].y, 2))

        side1 = math.sqrt(math.pow(self.__vertices[1].x - self.vertices[2].x, 2) + math.pow(self.__vertices[1].y - self.vertices[2].y, 2))

        side2 = math.sqrt(math.pow(self.__vertices[2].x - self.vertices[3].x, 2) + math.pow(self.__vertices[2].y - self.vertices[3].y, 2))

        side3 = math.sqrt(math.pow(self.__vertices[3].x - self.vertices[0].x, 2) + math.pow(self.__vertices[3].y - self.vertices[0].y, 2))

        return side0, side1, side2, side3



    def smallest_x(self) -> float:
        """Returns the x-coordinate of the vertex with the smallest x-value of the four vertices of this
        quadrilateral."""
        minCoordinate = float("inf")
        for point in self.__vertices:
            if point.x < minCoordinate:
                minCoordinate = point.x

        return minCoordinate



    def __eq__(self, other: object) -> bool:
        try:
            return (self.vertices == other.vertices) and (type(self) == type(other))
        except:
            return False



    def __ne__(self, other: object) -> bool:
        return not self.__eq__(other)



    def __str__(self) -> str:
        printOut = 'type: ' + str(self.__class__) + " vertices: "
        for point in self.__vertices:
            printOut = printOut + " " + str(point) + ","
        return printOut