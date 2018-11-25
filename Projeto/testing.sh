#!/bin/bash

tests="A-01-01-M-ok A-01-02-M-ok A-01-03-M-ok A-01-04-M-ok A-02-01-M-ok A-02-02-M-ok A-02-03-M-ok A-02-06-M-ok A-02-07-M-ok A-02-11-M-ok A-03-01-M-ok A-03-02-M-ok A-04-01-M-ok A-04-02-M-ok A-05-01-M-ok A-06-01-M-ok A-06-02-M-ok A-06-03-M-ok A-07-01-M-ok A-07-03-M-ok A-08-01-M-ok A-08-02-M-ok A-08-03-M-ok A-09-01-M-ok A-09-03-M-ok"

(cd project && make && reset)
for test in $tests
do
	echo -e "\033[1;32mTest: $test\033[0m"
	(cd project && java -Dimport=../Tests-ei-daily-201811061934/auto-tests/$test.import -Din=../Tests-ei-daily-201811061934/auto-tests/$test.in -Dout=../Tests-ei-daily-201811061934/auto-tests/$test.outhyp sth.app.App && diff -b ../Tests-ei-daily-201811061934/auto-tests/$test.outhyp ../Tests-ei-daily-201811061934/auto-tests/expected/$test.out)
	echo ""
done

