from dec25.machine import TuringMachine
from dec25.parser import get_turing_machine_from_file

########
# Main #
########
if __name__ == "__main__":
	tm = get_turing_machine_from_file("input25.txt")
	tm.run()
	print("Diagnostic checksum:", tm.get_diagnostic_checksum())
