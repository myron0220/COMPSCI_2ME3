## @file Scene.py
#  @author Steven Kostiuk
#  @brief Contains the Scene type which is used to simulate a shape's movement
#  @date 2021-02-16
#  @details Simulates a shape's movement according to initial velocities
#  and unbalanced forces functions
from scipy.integrate import odeint

## @brief Scene is used to simulate a shape's movement


class Scene():
    ## @brief Constructor for class TriangleT
    #  @param s Shape
    #  @param Fx Unbalanced forces function for x coordinates
    #  @param Fy Unbalanced forces function for y coordinates
    #  @param vx Initial x velocity
    #  @param vy Initial y velocity
    def __init__(self, s, Fx, Fy, vx, vy):
        self.s = s
        self.Fx = Fx
        self.Fy = Fy
        self.vx = vx
        self.vy = vy

    ## @brief Getter method to get the shape
    #  @return shape
    def get_shape(self):
        return self.s

    ## @brief Getter method to get the unbalanced forces functions
    #  @return unbalanced forces functions
    def get_unbal_forces(self):
        return self.Fx, self.Fy

    ## @brief Getter method to get the initial velocities of the shape
    #  @return initial velocities
    def get_init_velo(self):
        return self.vx, self.vy

    ## @brief Setter method to set the shape
    #  @param s shape
    def set_shape(self, s):
        self.s = s

    ## @brief Setter method to set the unbalanced forces functions
    #  @param Fx Unbalanced forces function for x coordinates
    #  @param Fy Unbalanced forces function for y coordinates
    def set_unbal_forces(self, Fx, Fy):
        self.Fx = Fx
        self.Fy = Fy

    ## @brief Setter method to set the initial velocities of the shape
    #  @param vx Initial x velocity
    #  @param vy Initial y velocity
    def set_init_velo(self, vx, vy):
        self.vx = vx
        self.vy = vy

    ## @brief Simulation method that simulates the movement of a shape
    #  @param tfinal Final time which the simulation runs until
    #  @param nsteps Number of steps which the time will be divided into
    #  @return sequence of real numbers representing the time and a
    #  sequence containing 4 sequences of real numbers representing x and y values
    def sim(self, tfinal, nsteps):
        t = [(i * tfinal) / (nsteps - 1) for i in range(nsteps)]
        return t, odeint(self.__ode, [self.s.cm_x(), self.s.cm_y(), self.vx, self.vy], t)

    ## @brief Ordinary differential equation method
    #  @param w List of center of masses and initial velocities
    #  @param t List of time in natural ascending order
    #  @return List of initial velocities and the results of
    #  the unbalanced forces functions
    def __ode(self, w, t):
        return [w[2], w[3], self.Fx(t) / self.s.mass(), self.Fy(t) / self.s.mass()]
