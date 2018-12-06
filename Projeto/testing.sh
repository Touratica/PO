#!/bin/bash

(cd project && make && reset)
for test in Tests-ef-daily-201812032309/auto-tests/*.in
do
	test=${test##*/}
	test=${test%.in}
	echo -e "\033[1;32mTest: ${test}\033[0m"
	(cd project && java -Dimport="../Tests-ef-daily-201812032309/auto-tests/${test}.import" -Din="../Tests-ef-daily-201812032309/auto-tests/${test}.in" -Dout="../Tests-ef-daily-201812032309/auto-tests/${test}.outhyp" sth.app.App && diff -s -b "../Tests-ef-daily-201812032309/auto-tests/${test}.outhyp" "../Tests-ef-daily-201812032309/auto-tests/expected/${test}.out" > ../tmp.txt)
	if [ $? -eq 0 ]; then
		echo -e "\033[1;31mThe files are identical\033[0m"
	else
		cat tmp.txt
	fi
	rm tmp.txt
	echo ""
done

echo ""
(cd project && make clean)