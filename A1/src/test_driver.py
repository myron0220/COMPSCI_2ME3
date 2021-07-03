## @file test_driver.py
#  @author Mingzhe Wang
#  @brief test cases for complex and triangle ADT
#  @date Jan 20, 2021

import math
from complex_adt import ComplexT
from triangle_adt import TriangleT, TriType

# Note: this is cited from the previous year 2017 Assignment test.
def isClose(a, b, rel_tol = 1e-09, abs_tol = 0.0):
    return abs(a - b) <= max(rel_tol * max(abs(a), abs(b)), abs_tol)

# approximate equal of complex number for test ONLY.
# private method.
def __appEqualCom__(comA, comB):
	if isClose(comA.real(), comB.real()) and isClose(comA.imag(), comB.imag()):
		return True
	else:
		return False

def test_complex_con():
	global testTot, passed
	testTot += 1
	try:
		c1 = ComplexT(5.0, 1.921)
		passed += 1
		print("test_complex_con() test: PASSED.")
	except:
		print("test_complex_con() test: FAILED.")

def test_real():
	global testTot, passed
	testTot += 1
	try:
		assert isClose(c1.real(), 1.0)
		assert isClose(c2.real(), 1e10)
		assert isClose(c3.real(), -1e10)
		assert isClose(c4.real(), 1.4142135623730951)
		assert isClose(c5.real(), -2.0)
		passed += 1
		print("real() test: PASSED.")
	except AssertionError:
		print("real() test: FAILED.")

def test_imag():
	global testTot, passed
	testTot += 1
	try:
		assert isClose(c1.imag(), 1.0)
		assert isClose(c2.imag(), 1e10)
		assert isClose(c3.imag(), -1e10)
		assert isClose(c4.imag(), -1.4142135623730951)
		assert isClose(c5.imag(), 3.4641016151377544)
		passed += 1
		print("imag() test: PASSED.")
	except AssertionError:
		print("imag() test: FAILED.")

def test_get_r():
	global testTot, passed
	testTot += 1
	try:
		assert isClose(c1.get_r(), math.sqrt(2))
		assert isClose(c2.get_r(), math.sqrt(2) * 10 ** 10)
		assert isClose(c3.get_r(), math.sqrt(2) * 10 ** 10)
		assert isClose(c4.get_r(), 2.0)
		assert isClose(c5.get_r(), 4.0)
		passed += 1
		print("get_r() test: PASSED.")
	except AssertionError:
		print("get_r() test: FAILED.")

def test_get_phi_branch1():
	global testTot, passed
	testTot += 1
	try:
		assert isClose(c1.get_phi(), math.pi / 4)
		assert isClose(c2.get_phi(), math.pi / 4)
		assert isClose(c3.get_phi(), (-3.0 / 4.0) * math.pi)
		assert isClose(c4.get_phi(), -math.pi / 4)
		assert isClose(c5.get_phi(), (2.0 / 3.0) * math.pi)
		passed += 1
		print("get_phi() not (real < 0 and imag = 0) test: PASSED.")
	except AssertionError:
		print("get_phi() not (real < 0 and imag = 0) test: FAILED.")

def test_get_phi_branch2():
	global testTot, passed
	testTot += 1
	try:
		c1 = ComplexT(-1e10, 0.0)
		c2 = ComplexT(-math.sqrt(2), 0.0)
		assert isClose(c1.get_phi(), math.pi)
		assert isClose(c2.get_phi(), math.pi)
		passed += 1
		print("get_phi() (real < 0 and imag = 0) test: PASSED.")
	except AssertionError:
		print("get_phi() (real < 0 and imag = 0) test: FAILED.")

# test for exact equal.
def test_com_equal():
	global testTot, passed
	testTot += 1
	try:
		ct1 = ComplexT(1.4142135623730951, -1.4142135623730951)
		ct2 = ComplexT(math.sqrt(2), -math.sqrt(2))
		assert c1.equal(ct1) == False
		assert c2.equal(ct1) == False
		assert c3.equal(ct1) == False
		assert c4.equal(ct1) == True
		assert c5.equal(ct1) == False
		assert c1.equal(ct2) == False
		assert c2.equal(ct2) == False
		assert c3.equal(ct2) == False
		assert c4.equal(ct2) == True
		assert c5.equal(ct2) == False
		passed += 1
		print("equal() test: PASSED.")
	except AssertionError:
		print("equal() test: FAILED.")

def test_conj():
	global testTot, passed
	testTot += 1
	try:
		assert __appEqualCom__(c1.conj(), ComplexT(1.0,-1.0))
		assert __appEqualCom__(c2.conj(), ComplexT(1e10,-1e10))
		assert __appEqualCom__(c3.conj(), ComplexT(-1e10,1e10))
		assert __appEqualCom__(c4.conj(), ComplexT(math.sqrt(2),math.sqrt(2)))
		assert __appEqualCom__(c5.conj(), ComplexT(-2.0,-2 * math.sqrt(3)))
		passed += 1
		print("conj() test: PASSED.")
	except AssertionError:
		print("conj() test: FAILED.")

def test_add():
	global testTot, passed
	testTot += 1
	try:
		ct = ComplexT(math.sqrt(5), 10.982)
		ans1 = ComplexT(1.0 + math.sqrt(5),11.982)
		ans2 = ComplexT(1e10 + math.sqrt(5),1e10 + 10.982)
		ans3 = ComplexT(-1e10 + math.sqrt(5),-1e10 + 10.982)
		ans4 = ComplexT(math.sqrt(2) + math.sqrt(5),-math.sqrt(2) + 10.982)
		ans5 = ComplexT(-2.0 + math.sqrt(5),2 * math.sqrt(3) + 10.982)
		assert __appEqualCom__(c1.add(ct), ans1)
		assert __appEqualCom__(c2.add(ct), ans2)
		assert __appEqualCom__(c3.add(ct), ans3)
		assert __appEqualCom__(c4.add(ct), ans4)
		assert __appEqualCom__(c5.add(ct), ans5)
		passed += 1
		print("add() test: PASSED.")
	except AssertionError:
		print("add() test: FAILED.")

def test_sub():
	global testTot, passed
	testTot += 1
	try:
		ct = ComplexT(1e10, 2e10)
		ans1 = ComplexT(-9999999999.0,-19999999999.0)
		ans2 = ComplexT(0.0,-1e10)
		ans3 = ComplexT(-2e10,-3e10)
		ans4 = ComplexT(-9999999998.585787,-20000000001.414215)
		ans5 = ComplexT(-10000000002.0,-19999999996.5359)
		assert __appEqualCom__(c1.sub(ct), ans1)
		assert __appEqualCom__(c2.sub(ct), ans2)
		assert __appEqualCom__(c3.sub(ct), ans3)
		assert __appEqualCom__(c4.sub(ct), ans4)
		assert __appEqualCom__(c5.sub(ct), ans5)
		passed += 1
		print("sub() test: PASSED.")
	except AssertionError:
		print("sub() test: FAILED.")

def test_mult():
	global testTot, passed
	testTot += 1
	try:
		ct1 = ComplexT(2.6,3.8)
		ct2 = ComplexT(2e10,2e10)
		ct3 = ComplexT(1e9,2e8)
		ct4 = ComplexT(math.sqrt(6),2 * math.sqrt(2))
		ans1 = ComplexT(-1.2,6.4)
		ans2 = ComplexT(0.0,4.0 * 10 ** 20)
		ans3 = ComplexT(-8e18,-12e18)
		ans4 = ComplexT(2*math.sqrt(3) + 4,4 - 2*math.sqrt(3))
		assert __appEqualCom__(c1.mult(ct1), ans1)
		assert __appEqualCom__(c2.mult(ct2), ans2)
		assert __appEqualCom__(c3.mult(ct3), ans3)
		assert __appEqualCom__(c4.mult(ct4), ans4)
		passed += 1
		print("mult() test: PASSED.")
	except AssertionError:
		print("mult() test: FAILED.")

def test_recip():
	global testTot, passed
	testTot += 1
	try:
		ans1 = ComplexT(0.5,-0.5)
		ans2 = ComplexT(1.0 / (2 * 10 ** 10),-1.0 / (2 * 10 ** 10))
		ans3 = ComplexT(-1.0 / (2 * 10 ** 10),1.0 / (2 * 10 ** 10))
		ans4 = ComplexT(math.sqrt(2) / 4,math.sqrt(2) / 4)
		ans5 = ComplexT(-1 / 8.0, -math.sqrt(3) / 8)
		assert __appEqualCom__(c1.recip(), ans1)
		assert __appEqualCom__(c2.recip(), ans2)
		assert __appEqualCom__(c3.recip(), ans3)
		assert __appEqualCom__(c4.recip(), ans4)
		assert __appEqualCom__(c5.recip(), ans5)
		passed += 1
		print("recip() test: PASSED.")
	except AssertionError:
		print("recip() test: FAILED.")

def test_div():
	global testTot, passed
	testTot += 1
	try:
		ct1 = ComplexT(2.0, 3.0)
		ct2 = ComplexT(-3e10, 4e10)
		ct3 = ComplexT(-3e10, 4e10)
		ct4 = ComplexT(math.sqrt(3), 2 * math.sqrt(3))
		ct5 = ComplexT(6.0, 10.0)
		ans1 = ComplexT(5/13.0, -1/13.0)
		ans2 = ComplexT(1/25.0, -7/25.0)
		ans3 = ComplexT(-1/25.0, 7/25.0)
		ans4 = ComplexT(-math.sqrt(6)/15, -math.sqrt(6)/5)
		ans5 = ComplexT((5 * math.sqrt(3) - 3)/34, (5 + 3 * math.sqrt(3))/34)
		assert __appEqualCom__(c1.div(ct1), ans1)
		assert __appEqualCom__(c2.div(ct2), ans2)
		assert __appEqualCom__(c3.div(ct3), ans3)
		assert __appEqualCom__(c4.div(ct4), ans4)
		assert __appEqualCom__(c5.div(ct5), ans5)
		passed += 1
		print("div() test: PASSED.")
	except AssertionError:
		print("div() test: FAILED.")

def test_sqrt_branch1():
	global testTot, passed
	testTot += 1
	try:
		ca1 = ComplexT(math.sqrt((1 + math.sqrt(2))/2), 
			math.sqrt((-1 + math.sqrt(2))/2))
		ca2 = ComplexT(10**5 * math.sqrt((1 + math.sqrt(2))/2), 
			10**5 * math.sqrt((-1 + math.sqrt(2))/2))
		ca5 = ComplexT(1.0, math.sqrt(3))
		assert __appEqualCom__(c1.sqrt(), ca1)
		assert __appEqualCom__(c2.sqrt(), ca2)
		assert __appEqualCom__(c5.sqrt(), ca5)
		passed += 1
		print("sqrt() (imag > 0) test: PASSED.")
	except AssertionError:
		print("sqrt() (imag > 0) test: FAILED.")

def test_sqrt_branch2():
	global testTot, passed
	testTot += 1
	try:
		ca3 = ComplexT(10**5 * math.sqrt((-1 + math.sqrt(2))/2), 
			-10**5 * math.sqrt((1 + math.sqrt(2))/2))
		ca4 = ComplexT(math.sqrt((math.sqrt(2)+2)/2), 
			-math.sqrt((-math.sqrt(2)+2)/2))
		assert __appEqualCom__(c3.sqrt(), ca3)
		assert __appEqualCom__(c4.sqrt(), ca4)
		passed += 1
		print("sqrt() (imag < 0) test: PASSED.")
	except AssertionError:
		print("sqrt() (imag < 0) test: FAILED.")

def test_triangle_con_normal():
	global testTot, passed
	testTot += 1
	try:
		t1 = TriangleT(10,1,2)
		passed += 1
		print("test_triangle_con() normal test: PASSED")
	except:
		print("test_triangle_con() normal test: FAILED")

def test_triangle_con_except():
	global testTot, passed
	testTot += 1
	try:
		t1 = TriangleT(-1,10,9)
		print("test_triangle_con() except test: FAILED")
	except ValueError:
		passed += 1
		print("test_triangle_con() except test: PASSED")
	except:
		print("test_triangle_con() except test: FAILED")

# need to test is_Valid first.
def test_get_sides():
	global testTot, passed
	testTot += 1
	t1 = TriangleT(3,4,5)
	t2 = TriangleT(4,5,6)
	t3 = TriangleT(10,1,2)
	try:
		assert t1.get_sides() == (3,4,5)
		assert t2.get_sides() == (4,5,6)
		assert t3.get_sides() == (10,1,2)
		passed += 1
		print("get_sides() test: PASSED.")
	except AssertionError:
		print("get_sides() test: FAILED.")

def test_tri_equal_true():
	global testTot, passed
	testTot += 1
	try:
		t1 = TriangleT(3, 4, 5)
		t2 = TriangleT(4, 5, 6)
		t3 = TriangleT(5, 3, 4)
		assert t1.equal(t1) == True
		assert t1.equal(t3) == True
		assert t3.equal(t1) == True
		passed += 1
		print("equal() true test: PASSED.")
	except AssertionError:
		print("equal() true test: FAILED.")

def test_tri_equal_false():
	global testTot, passed
	testTot += 1
	try:
		t1 = TriangleT(3, 4, 5)
		t2 = TriangleT(4, 5, 6)
		t3 = TriangleT(5, 3, 4)
		assert t1.equal(t2) == False
		assert t2.equal(t3) == False
		assert t2.equal(t1) == False
		assert t3.equal(t2) == False
		passed += 1
		print("equal() false test: PASSED.")
	except AssertionError:
		print("equal() false test: FAILED.")

def test_perim():
	global testTot, passed
	testTot += 1
	try:
		t1 = TriangleT(3, 4, 5)
		t2 = TriangleT(10, 7, 8)
		t3 = TriangleT(11111,22222,33000)
		assert t1.perim() == 12
		assert t2.perim() == 25
		assert t3.perim() == 66333
		passed += 1
		print("perim() test: PASSED.")
	except AssertionError:
		print("perim() test: FAILED.")

def test_area():
	global testTot, passed
	testTot += 1
	try:
		t1 = TriangleT(3,4,5)
		t2 = TriangleT(5,6,7)
		t3 = TriangleT(1111111111,2222222222,3333333330)
		assert isClose(t1.area(), 6.0)
		assert isClose(t2.area(), 6 * math.sqrt(6))
		assert isClose(t3.area(), 111111110956944.45)
		passed += 1
		print("area() test: PASSED.")
	except AssertionError:
		print("area() test: FAILED.")

def test_is_valid():
	global testTot, passed
	testTot += 1
	try:
		t1 = TriangleT(3,4,5)
		t2 = TriangleT(10,2,1)
		t3 = TriangleT(2,4,6)
		assert t1.is_valid()
		assert not t2.is_valid()
		assert not t3.is_valid()
		passed += 1
		print("is_valid() test: PASSED.")
	except AssertionError:
		print("is_valid() test: FAILED.")

def test_tri_type_right():
	global testTot, passed
	testTot += 1
	try:
		t1 = TriangleT(3,4,5)
		t2 = TriangleT(4,5,3)
		t3 = TriangleT(5,3,4)
		assert t1.tri_type() == TriType.right
		assert t2.tri_type() == TriType.right
		assert t3.tri_type() == TriType.right
		passed += 1
		print("tri_type() right test: PASSED.")
	except AssertionError:
		print("tri_type() right test: FAILED.")

def test_tri_type_equilat():
	global testTot, passed
	testTot += 1
	try:
		t2 = TriangleT(1,1,1)
		assert t2.tri_type() == TriType.equilat
		passed += 1
		print("tri_type() equilat test: PASSED.")
	except AssertionError:
		print("tri_type() equilat test: FAILED.")

def test_tri_type_isosceles():
	global testTot, passed
	testTot += 1
	try:
		t3 = TriangleT(2,2,3)
		assert t3.tri_type() == TriType.isosceles
		passed += 1
		print("tri_type() isosceles test: PASSED.")
	except AssertionError:
		print("tri_type() isosceles test: FAILED.")

def test_tri_type_scalene():
	global testTot, passed
	testTot += 1
	try:
		t4 = TriangleT(10,11,12)
		assert t4.tri_type() == TriType.scalene
		passed += 1
		print("tri_type() scalene test: PASSED.")
	except AssertionError:
		print("tri_type() scalene test: FAILED.")

# Rationale:
# 1. Choose test cases that test more complex methods.
# 2. Choose test cases that can be verified in another way easily.
# 3. Choose different types of numbers: negative numbers, rational number, 
#    irrational number etc.
# 4. Choose the numbers in the edge like large numbers or small numbers.

testTot = 0
passed = 0

print("-------------------------Tests for complex_adt.py--------------------")
test_complex_con()
c1 = ComplexT(1.0,1.0)
c2 = ComplexT(1e10,1e10)
c3 = ComplexT(-1e10,-1e10)
c4 = ComplexT(math.sqrt(2), -math.sqrt(2))
c5 = ComplexT(-2.0, 2 * math.sqrt(3))
test_real()
test_imag()
test_get_r()
test_get_phi_branch1()
test_get_phi_branch2()
test_com_equal()
test_conj()
test_add()
test_sub()
test_mult()
test_recip()
test_div()
test_sqrt_branch1()
test_sqrt_branch2()
print("-------------------------Tests for triangle_adt.py-------------------")
test_triangle_con_normal()
test_triangle_con_except()
test_get_sides()
test_tri_equal_true()
test_tri_equal_false()
test_perim()
test_area()
test_is_valid()
test_tri_type_right()
test_tri_type_equilat()
test_tri_type_isosceles()
test_tri_type_scalene()
print("------------------------------------Total----------------------------")
print("Passed test number: ", passed)
print("Total test number: ", testTot)

