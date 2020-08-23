# Nitin Dev
# NDEV
# 112298641

from quadrilateral import Quadrilateral

class ShapeSorter:

    @staticmethod
    def sort(*args) -> list:
        list_of_shapes = list(args)

        try:
            ALL_SHAPES = True

            for arg in list_of_shapes:
                if not isinstance(arg, Quadrilateral):
                    ALL_SHAPES = False

            if ALL_SHAPES:
                num = len(list_of_shapes)
                for x in range(num):
                    for y in range(0, num - x - 1):
                        if (list_of_shapes[y].smallest_x()) > (list_of_shapes[y + 1].smallest_x()):
                            list_of_shapes[y], list_of_shapes[y + 1] = list_of_shapes[y + 1], list_of_shapes[y]

            return list_of_shapes

        except:
            return list_of_shapes
