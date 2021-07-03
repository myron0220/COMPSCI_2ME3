#Path separator is different on Windows versus Unix based OSes
ifeq ($(OS),Windows_NT)
	SEP=;
else
	SEP=:
endif

JFLAGS = -g
JCLASS = -cp "src$(SEP).$(SEP)../junit-4.5.jar"
JC = javac
JVM = java

.PHONY: test expt

test:
	find . -name '*.class' -exec rm -f {} \;
	$(JC) $(JCLASS) $(JFLAGS) src/TestSeq1D.java
	$(JVM) $(JCLASS) org.junit.runner.JUnitCore src.TestSeq1D

expt:
	$(JC) $(JCLASS) $(JFLAGS) src/Expt.java
	$(JVM) src/Expt

clean:
	cd src
	rm **/*.class