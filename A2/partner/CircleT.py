## @file CircleT.py
#  @author Steven Kostiuk
#  @brief Contains the CircleT type to represent shapes that are circles
#  @date 2021-02-16
from Shape import Shape

## @brief CircleT is used to represent shapes that are circles


class CircleT(Shape):
    ## @brief Constructor for class CircleT
    #  @param xs X value of the center of mass
    #  @param ys Y value of the center of mass
    #  @param rs Radius of the circle
    #  @param ms Mass of the circle
    #  @throws ValueError Thrown if the mass or radius of circle is less than or equal to zero
    def __init__(self, xs, ys, rs, ms):
        if not (rs > 0 and ms > 0):
            raise ValueError
        self.x = xs
        self.y = ys
        self.r = rs
        self.m = ms

    ## @brief Getter method that gets the x value of the center of mass
    #  @return x value of the center of mass
    def cm_x(self):
        return self.x

    ## @brief Getter method that gets the y value of the center of mass
    #  @return y value of the center of mass
    def cm_y(self):
        return self.y

    ## @brief Getter method that gets the mass of the circle
    #  @return mass of the circle
    def mass(self):
        return self.m

    ## @brief Getter method that gets the moment of inertia of the circle
    #  @return moment of inertia of the circle
    def m_inert(self):
        return (self.m * (self.r ** 2)) / 2
