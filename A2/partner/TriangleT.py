## @file TriangleT.py
#  @author Steven Kostiuk
#  @brief Contains the TriangleT type to represent shapes that are triangles
#  @date 2021-02-16
from Shape import Shape

## @brief TriangleT is used to represent shapes that are triangles


class TriangleT(Shape):
    ## @brief Constructor for class TriangleT
    #  @param xs X value of the center of mass
    #  @param ys Y value of the center of mass
    #  @param rs Side of the triangle
    #  @param ms Mass of the triangle
    #  @throws ValueError Thrown if the mass or side of triangle is less than or equal to zero
    def __init__(self, xs, ys, ss, ms):
        if not (ss > 0 and ms > 0):
            raise ValueError
        self.x = xs
        self.y = ys
        self.s = ss
        self.m = ms

    ## @brief Getter method that gets the x value of the center of mass
    #  @return x value of the center of mass
    def cm_x(self):
        return self.x

    ## @brief Getter method that gets the y value of the center of mass
    #  @return y value of the center of mass
    def cm_y(self):
        return self.y

    ## @brief Getter method that gets the mass of the triangle
    #  @return mass of the triangle
    def mass(self):
        return self.m

    ## @brief Getter method that gets the moment of inertia of the triangle
    #  @return moment of inertia of the triangle
    def m_inert(self):
        return (self.m * (self.s ** 2)) / 12
