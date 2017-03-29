# Generic Makefile

LIB = lib
SRCDIR = src
BINDIR = bin
TESTDIR = test
DOCDIR = doc

CLI = $(LIB)/cli/commons-cli-1.3.1.jar
ASM = $(LIB)/asm/asm-5.0.4.jar:$(LIB)/asm/asm-commons-5.0.4.jar:$(LIB)/asm/asm-tree-5.0.4.jar
JUNIT = $(LIB)/junit/junit-4.12.jar:$(LIB)/junit/hamcrest-core-1.3.jar
JACOCO = $(LIB)/jacoco/org.jacoco.core-0.7.5.201505241946.jar:$(LIB)/jacoco/org.jacoco.report-0.7.5.201505241946.jar:
TOOLS = $(LIB)/tools

CLASSPATH=.:$(LIB)/junit-4.10.jar:$(LIB)/hamcrest-core-1.3.jar:$(SRC)/:$(BIN)/

JAVAC = javac
JFLAGS = -g -Xlint:unchecked -d $(BINDIR) -cp $(BINDIR):$(JUNIT) 

vpath %.java $(SRCDIR):$(TESTDIR)
vpath %.class $(BINDIR)

# Define general build rule for java sources
.SUFFIXES:  .java  .class

.java.class:
	$(JAVAC)  $(JFLAGS)  $<

# Default rule - will be invoked by make
all:  Message.class Listener.class Writer.class User.class Server.class ServerApp.class HelloWorld.class


# Rules for unit testing
test_classes: all \
	TestHelloWorld.class \
	TestMessage.class \
	TestSuite.class

test: test_classes
	java -ea -cp $(BINDIR):$(JUNIT) org.junit.runner.JUnitCore TestSuite


run:
	java -ea -cp $(BINDIR) server.ServerApp

# Rules for generating documentation
doc:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java $(TESTDIR)/TestUtils.java

# Rules for generating tests
jacoco.exec: test_classes
	java -ea -javaagent:$(LIB)/jacoco/jacocoagent.jar -cp $(BINDIR):$(JUNIT) org.junit.runner.JUnitCore TestSuite

report: jacoco.exec
	java -cp $(BINDIR):$(CLI):$(JACOCO):$(ASM):$(TOOLS) Report --reporttype html .

# Rule for clean
clean:
	@rm -f  $(BINDIR)/*.class
	@rm -f jacoco.exec *.xml *.csv
	@rm -Rf coveragereport
	@rm -Rf doc
	@rm *~
	@rm */*~
	@rm */**/*~

