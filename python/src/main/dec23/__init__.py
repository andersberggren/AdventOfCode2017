from aoclib.filereader import get_file_as_list_of_string
from dec23.cpu import CPU

########
# Main #
########
if __name__ == "__main__":
	cpu = CPU()
	instructions = [line.strip().split() for line in get_file_as_list_of_string("input23.txt")]
	cpu.exec_instructions(instructions)
	print("mul is executed {} times".format(cpu.n_mul))
	
	cpu = CPU(shortcuts_enabled=True)
	cpu.set_register("a", 1)
	cpu.exec_instructions(instructions)
	print("h:", cpu.get_value("h"))
