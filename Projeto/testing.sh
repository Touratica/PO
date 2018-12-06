#!/bin/bash

(cd project && make > ../log.txt)
if [ $? -eq 0 ]; then
		rm log.txt
	else
		cat log.txt
		exit
	fi
	i=0
	j=0
for test in Tests-ef-daily-201812032309/auto-tests/*.in
do
	((i++))
	test=${test##*/}
	test=${test%.in}
	echo -e "\033[1;32mTest: ${test}\033[0m"
	(cd project && java -Dimport="../Tests-ef-daily-201812032309/auto-tests/${test}.import" -Din="../Tests-ef-daily-201812032309/auto-tests/${test}.in" -Dout="../Tests-ef-daily-201812032309/auto-tests/${test}.outhyp" sth.app.App && diff -s -b "../Tests-ef-daily-201812032309/auto-tests/${test}.outhyp" "../Tests-ef-daily-201812032309/auto-tests/expected/${test}.out" > ../tmp.txt)
	if [ $? -eq 0 ]; then
		echo -e "\033[1;31mThe files are identical\033[0m"
		((j++))
	else
		cat tmp.txt
	fi
	rm tmp.txt
	echo ""
done

echo "Passed $j of $i tests"
(cd project && make clean > /dev/null)