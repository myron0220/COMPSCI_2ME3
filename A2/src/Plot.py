## @file Plot.py
#  @author Mingzhe Wang (wangm235)
#  @brief A plot script.
#  @date 2021 Feb 16
#  @details Plot used to output the simulation result in three figures.

import matplotlib.pyplot as plt


## @brief This plot method will plot the t vs x, t vs y, x vs y figures in a vertical dire
#         ction as showed in the assignment requirement.
#  @details Implementation of Plot.py is facilitated by the matplotlib package.
#  @param w A seq of 4-length-seq of real number representing [x, y, vx, vy] in different time
#           t.
#  @param t A seq of real number representing the dicrete time.
#  @throws ValueError If the two input seqs has different lengths.
def plot(w, t):
    if not (len(w) == len(t)):
        raise ValueError
    x = [w_ele[0] for w_ele in w]
    y = [w_ele[1] for w_ele in w]

    fig, axs = plt.subplots(nrows=3, ncols=1)
    fig.suptitle('Motion Simulation')
    axs[0].plot(t, x)
    axs[1].plot(t, y)
    axs[2].plot(x, y)
    axs[0].set(ylabel='x (m)')
    axs[1].set(ylabel='y (m)')
    axs[2].set(ylabel='y (m)')
    axs[2].set(xlabel='x (m)')
    plt.show()
