## @file test_All.py
#  @author Mingzhe Wang (wangm235)
#  @brief Test all the functions in this assignment.
#  @date 2021 Feb 8
#  @details pass.

import pytest
from pytest import approx
import math
from CircleT import *
from TriangleT import *
from BodyT import *
from Scene import *
from Plot import *


class TestCircleT:

    def setup_method(self, method):
        self.circle1 = CircleT(0.0, 0.0, 4.0, 2.0)
        self.circle2 = CircleT(-2.3e10, 2.3395934, math.sqrt(2), 5.3e10)

    def teardown_method(self, method):
        self.circle1 = None
        self.circle2 = None

    def test_init_exception_both_invalid(self):
        with pytest.raises(ValueError):
            CircleT(0.0, 0.0, 0.0, 0.0)

    def test_init_exception_second_invalid(self):
        with pytest.raises(ValueError):
            CircleT(7.03, 5.02, 1.0, -123.1)

    def test_init_exception_first_invalid(self):
        with pytest.raises(ValueError):
            CircleT(4.213, 3.4122, -1e10, 1e10)

    def test_cm_x(self):
        assert self.circle1.cm_x() == approx(0.0)
        assert self.circle2.cm_x() == approx(-2.3e10)

    def test_cm_y(self):
        assert self.circle1.cm_y() == approx(0.0)
        assert self.circle2.cm_y() == approx(2.3395934)

    def test_mass(self):
        assert self.circle1.mass() == approx(2.0)
        assert self.circle2.mass() == approx(5.3e10)

    def test_m_inert(self):
        assert self.circle1.m_inert() == approx(16.0)
        assert self.circle2.m_inert() == approx(5.3e10)


class TestTriangleT:

    def setup_method(self, method):
        self.triangle1 = TriangleT(4.6e3, 9.123, math.sqrt(6), 12.0)
        self.triangle2 = TriangleT(5.02, 23.1, 1.0, 5.389)

    def teardown_method(self, method):
        self.triangle1 = None
        self.triangle2 = None

    def test_init_exception_both_invalid(self):
        with pytest.raises(ValueError):
            TriangleT(0.0, 0.0, 0.0, 0.0)

    def test_init_exception_second_invalid(self):
        with pytest.raises(ValueError):
            TriangleT(7.0313, 302, 0.00002, -0.000001)

    def test_init_exception_first_invalid(self):
        with pytest.raises(ValueError):
            TriangleT(4.213, math.pi, -1.0 * 23, 12.123)

    def test_cm_x(self):
        assert self.triangle1.cm_x() == approx(4.6e3)
        assert self.triangle2.cm_x() == approx(5.02)

    def test_cm_y(self):
        assert self.triangle1.cm_y() == approx(9.123)
        assert self.triangle2.cm_y() == approx(23.1)

    def test_mass(self):
        assert self.triangle1.mass() == approx(12.0)
        assert self.triangle2.mass() == approx(5.389)

    def test_m_inert(self):
        assert self.triangle1.m_inert() == approx(6.0)
        assert self.triangle2.m_inert() == approx(5.389 / 12)


class TestBodyT:

    def setup_method(self, method):
        self.body1 = BodyT([-1.0, 1.0, 1.0, -1.0], [-1.0, -1.0, 1.0, 1.0], [2.33412,
                           2.33412, 2.33412, 2.33412])
        self.body2 = BodyT([-1.0, 1.0, 0.0], [0.0, 0.0, math.sqrt(3)], [2.8, 2.8, 2.8])
        self.body3 = BodyT([0.0, 0.0, 0.0], [0.0, 0.0, 0.0], [1.5, 2.83, 3.2])

    def teardown_method(self, method):
        self.body1 = None
        self.body2 = None
        self.body3 = None

    def test_init_exception_len_not_equal(self):
        with pytest.raises(ValueError):
            BodyT([1.0, 2.0, 3.12], [0.12], [1.0, 5.3, 7.9])
        with pytest.raises(ValueError):
            BodyT([0.0], [0.0], [0.0, 0.0])

    def test_init_exception_m_not_pos(self):
        with pytest.raises(ValueError):
            BodyT([1.2, math.pi, 12.0], [2.3, 12.3, 5.31], [2.3, 12.3, -1e10])
        with pytest.raises(ValueError):
            BodyT([-12.2, 0.0], [-5.7, 0.1], [1.4, 0.0])

    def test_cm_x(self):
        assert self.body1.cm_x() == approx(0.0)
        assert self.body2.cm_x() == approx(0.0)
        assert self.body3.cm_x() == approx(0.0)

    def test_cm_y(self):
        assert self.body1.cm_y() == approx(0.0)
        assert self.body2.cm_y() == approx(1 * math.sqrt(3) / 3.0)
        assert self.body3.cm_y() == approx(0.0)

    def test_mass(self):
        assert self.body1.mass() == approx(4 * 2.33412)
        assert self.body2.mass() == approx(3 * 2.8)
        assert self.body3.mass() == approx(1.5 + 2.83 + 3.2)

    def test_m_inert(self):
        self.body4 = BodyT([-1.0, 1.0, 0.0], [0.0, 0.0, math.sqrt(3)], [1.0, 1.0, 1.0])
        assert self.body1.m_inert() == approx(8 * 2.33412)
        assert self.body2.m_inert() == approx(2.8 * 4.0)
        assert self.body3.m_inert() == approx(0.0)
        assert self.body4.m_inert() == approx(4.0)


class TestScene:

    # No test for get_unbal_forces() and set_unbal_forces(), as specified in the requirement.
    def setup_method(self, method):

        # global settings with some function only for s1 and s2.
        g = 9.81
        m = 1.0

        def F_0(t):
            return 0.0

        def F_g_s1s2(t):
            return - g * m

        def F_x_s2(t):
            return 10.923

        # scene1: falling movement for a circle object
        self.circle1 = CircleT(10.0, 10.0, 1.0, m)
        self.scene1 = Scene(self.circle1, F_0, F_g_s1s2, 0.0, 0.0)

        # scene2: projectile movement for a triange object
        self.triangle1 = TriangleT(0.0, 0.0, 1.2, m)
        self.scene2 = Scene(self.triangle1, F_x_s2, F_g_s1s2, math.sqrt(3), 0.0)

        # scene3: general projectile movement for an object.
        # The settings can be changed later and need to update the corresponding test result.
        # all settings for scene3 are here
        m_in_ys = 2.3
        v = 5.6
        theta = math.pi / 4.0
        xs = [-1, 1, 0]
        ys = [0, 0, math.sqrt(3)]

        # input corresponding the setting
        vx = math.cos(theta) * v
        vy = math.sin(theta) * v
        ms = [m_in_ys, m_in_ys, m_in_ys]

        def F_g_s3(t):
            return - g * len(ms) * m_in_ys

        self.body1 = BodyT(xs, ys, ms)
        self.scene3 = Scene(self.body1, F_0, F_g_s3, vx, vy)

    def teardown_method(self, method):
        self.circle1 = None
        self.scene1 = None
        self.triangle1 = None
        self.scene2 = None
        self.body1 = None
        self.scene3 = None

    def test_get_shape(self):
        assert self.scene1.get_shape() == self.circle1
        assert self.scene2.get_shape() == self.triangle1
        assert self.scene3.get_shape() == self.body1

    def test_get_init_velo(self):
        assert self.scene1.get_init_velo()[0] == approx(0.0)
        assert self.scene1.get_init_velo()[1] == approx(0.0)
        assert self.scene2.get_init_velo()[0] == approx(math.sqrt(3))
        assert self.scene2.get_init_velo()[1] == approx(0.0)
        assert self.scene3.get_init_velo()[0] == approx((math.sqrt(2) / 2) * 5.6)
        assert self.scene3.get_init_velo()[1] == approx((math.sqrt(2) / 2) * 5.6)

    def test_set_shape(self):
        self.triangle1 = TriangleT(0.0, 0.0, 1.0, 5.82)
        self.scene1.set_shape(self.triangle1)
        assert self.scene1.get_shape() == self.triangle1
        self.body1 = BodyT([-1.0, 1.0, 0.0], [0.0, 0.0, math.sqrt(3)], [2.8, 2.8, 2.8])
        self.scene2.set_shape(self.body1)
        assert self.scene2.get_shape() == self.body1
        self.triangle1 = TriangleT(0.12, 9.12, 1.0, 5.23)
        self.scene3.set_shape(self.triangle1)
        assert self.scene3.get_shape() == self.triangle1

    def test_set_init_velo(self):

        self.scene1.set_init_velo(5.6, 1e10)
        assert self.scene1.get_init_velo()[0] == approx(5.6)
        assert self.scene1.get_init_velo()[1] == approx(1e10)
        self.scene2.set_init_velo(-0.12, 9.123)
        assert self.scene2.get_init_velo()[0] == approx(-0.12)
        assert self.scene2.get_init_velo()[1] == approx(9.123)
        self.scene3.set_init_velo(-1e10, 2.3)
        assert self.scene3.get_init_velo()[0] == approx(-1e10)
        assert self.scene3.get_init_velo()[1] == approx(2.3)

    @staticmethod
    def __compare_two_seqs(s_calc, s_true, epi):
        if len(s_calc) != len(s_true):
            raise ValueError("Must be two same-length seqs")
        max_abs_numer = 0.0
        max_abs_denom = 0.0
        for i in range(len(s_calc)):
            sub = s_calc[i] - s_true[i]
            abs_sub = abs(sub)
            abs_true = abs(s_true[i])
            if abs_sub > max_abs_numer:
                max_abs_numer = abs_sub
            if abs_true > max_abs_denom:
                max_abs_denom = abs_true
        if max_abs_numer / max_abs_denom < epi:
            return True
        else:
            return False

    def test_sim_scene1(self):
        t, wsol = self.scene1.sim(10, 101)
        t_counter = 0
        for t_s, wsol_s in zip(t, wsol):
            wsol_true = [10.0, 10.0 - 0.5 * 9.81 * t_counter**2, 0.0, - 9.81 * t_counter]
            assert t_s == approx(t_counter)
            assert TestScene.__compare_two_seqs(wsol_s, wsol_true, 0.001)
            t_counter += 0.1

    def test_sim_scene2(self):
        t, wsol = self.scene2.sim(10, 101)
        t_counter = 0
        for t_s, wsol_s in zip(t, wsol):
            wsol_true = [
                math.sqrt(3) * t_counter + 0.5 * 10.923 * t_counter**2,
                -0.5 * 9.81 * t_counter**2, math.sqrt(3) + 10.923 * t_counter,
                -9.81 * t_counter
            ]
            assert t_s == approx(t_counter)
            assert TestScene.__compare_two_seqs(wsol_s, wsol_true, 0.001)
            t_counter += 0.1

    def test_sim_scene3(self):
        t, wsol = self.scene3.sim(10, 101)
        t_counter = 0
        for t_s, wsol_s in zip(t, wsol):
            wsol_true = [
                (math.sqrt(2) / 2) * 5.6 * t_counter,
                math.sqrt(3) / 3 + math.sqrt(2) * 5.6 / 2 * t_counter - 4.905 * t_counter**2,
                (math.sqrt(2) / 2) * 5.6,
                (math.sqrt(2) / 2) * 5.6 - 9.81 * t_counter
            ]
            assert t_s == approx(t_counter)
            assert TestScene.__compare_two_seqs(wsol_s, wsol_true, 0.001)
            t_counter += 0.1
