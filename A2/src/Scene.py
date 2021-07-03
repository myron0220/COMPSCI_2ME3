## @file Scene.py
#  @author Mingzhe Wang (wangm235)
#  @brief A scene ADT that simulates the movement of an obeject of type Shape.
#  @date 2021 Feb 16
#  @details The object can be a circle, an equilateral triangle, or an general object consist
#           ing of finite mass points.s

from scipy.integrate import odeint


## @brief A class representing a scene for simulation.
#  @details A scene contains the detailed information of the movement of an obejct, such as sh
#           ape, unbalanced force, init_velocity, etc.
class Scene:

    ## @brief Constructor for Scene.
    #  @details Constructor taking in a shape type, two functions representing the unbalanced
    #           forces with time t in x and y coordinate, an horizontal initial velocity, and
    #           an vertial initial velocity to simulate an scene.
    #  @param s A Shape object representing the type of the object shape.
    #  @param fx A function of type real number -> real number representing the unbalanced for
    #            ces with time t in x-coordinate.
    #  @param fy A function of type real number -> real number representing the unbalanced for
    #            ces with time t in y-coordinate.
    #  @param vx A real number representing the horizontal initial velocity.
    #  @param vy A real number representing the vertial initial velocity.
    def __init__(self, s, fx, fy, vx, vy):
        ## Shape
        self.s = s
        ## unbalanced force function in x dir
        self.fx = fx
        ## unbalanced force function in y dir
        self.fy = fy
        ## initial velocity in x dir
        self.vx = vx
        ## initial velocity in y dir
        self.vy = vy

    ## @brief Returns the Shape object of the scene.
    #  @details The shape object records the information of the object.
    #  @return A Shape representing the object that is focused in this simulation.
    def get_shape(self):
        return self.s

    ## @brief Returns the tuple of the functions of unbalanced forces.
    #  @details The first element is the function of unbalanced force in x-coordinate.<br/>
    #           The second element is the function of unbalanced force in y-coordinate.
    #  @return A tupe of two functions of real number -> real number representing functions of
    #          unbalanced forces.
    def get_unbal_forces(self):
        return (self.fx, self.fy)

    ## @brief Returns the tuple of initial velocities.
    #  @details The first element is the initial velocities in x-coordinate.<br/>
    #           The second element is the initial velocities in y-coordinate.
    #  @return A tupe of two real number representing the unbalanced forces.
    def get_init_velo(self):
        return (self.vx, self.vy)

    ## @brief Set a new object for this scene.
    #  @param s A Shape representing the object that is focused in this simulation.
    def set_shape(self, s):
        self.s = s

    ## @brief Set a new pair of functions of unbalanced forces for this scene.
    #  @param fx A function of real number -> real number representing unbalanced force in x-c
    #            oordinate.
    #  @param fy A function of real number -> real number representing unbalanced force in y-c
    #            oordinate.
    def set_unbal_forces(self, fx, fy):
        self.fx = fx
        self.fy = fy

    ## @brief Set a new pair of initial velocities for this scene.
    #  @param fx A real number representing the initial velocity in x-coordinate.
    #  @param fy A real number representing the initial velocity in y-coordinate.
    def set_init_velo(self, vx, vy):
        self.vx = vx
        self.vy = vy

    ## @bried Begin a simulation in an time interval.
    #  @details The time of simulation will start from 0 to t_fin in a n_step interval.
    #  @param t_fin A real number representing the final time.
    #  @param  n_step A natural number representing the number of time intervals.
    #  @return A tuple (seq of real numbers, seq of 4-length-seq of real numbers). First elem-
    #          ent represents seq of time; second element represents [x, y, vx, vy] in diffe-
    #          rent time t.
    def sim(self, t_fin, n_step):
        t = []
        for i in range(n_step):
            t.append((i * t_fin) / (n_step - 1))
        sec_ag = odeint(self.__ode, [self.s.cm_x(), self.s.cm_y(), self.vx, self.vy], t)
        return (t, sec_ag)

    # A local fucntion used for solving the ode.
    def __ode(self, w, t):
        return [w[2], w[3], self.fx(t) / self.s.mass(), self.fy(t) / self.s.mass()]
