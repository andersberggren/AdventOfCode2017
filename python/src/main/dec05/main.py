from aoclib.filereader import get_file_as_string

class CPU:
	def __init__(self, instructions, get_instruction_increment):
		"""
		Arguments:
		  instructions               A list of integers
		  get_instruction_increment  A function that takes one argument (value at cursor),
		                             and returns the amount that the value should be incremented
		                             (before moving cursor).
		"""
		self.instructions = list(instructions)
		self.cursor = 0
		self.get_instruction_increment = get_instruction_increment
	
	def run_until_out_of_bounds(self):
		""" Returns number of steps executed. """
		n_steps = 0
		while self._step():
			n_steps += 1
		return n_steps
	
	def _step(self):
		if self.cursor < 0 or self.cursor >= len(self.instructions):
			return False
		jump_offset = self.instructions[self.cursor]
		self.instructions[self.cursor] += self.get_instruction_increment(jump_offset)
		self.cursor += jump_offset
		return True

instructions = [int(x) for x in get_file_as_string("input05.txt").split()]
get_instruction_increment_v1 = lambda x: 1
get_instruction_increment_v2 = lambda x: 1 if x < 3 else -1
for get_instruction_increment in [get_instruction_increment_v1, get_instruction_increment_v2]:
	cpu = CPU(instructions, get_instruction_increment)
	n_steps = cpu.run_until_out_of_bounds()
	print("Number of steps executed:", n_steps)
