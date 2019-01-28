class CPU:
	def __init__(self):
		self.registers = {}
		self.current_instruction = 0
		self.n_mul = 0
	
	def get_value(self, register_or_value):
		try:
			return int(register_or_value)
		except ValueError:
			try:
				return self.registers[register_or_value]
			except KeyError:
				self.registers[register_or_value] = 0
				return 0
	
	def set_register(self, register, value):
		self.registers[register] = value
	
	def exec_instructions(self, instructions):
		while True:
			try:
				instruction = instructions[self.current_instruction]
			except IndexError:
				break
			operation = instruction[0]
			args = instruction[1:]
			globals()["op_" + operation](self, args)
			self.current_instruction += 1
			if operation == "mul":
				self.n_mul += 1

def op_jnz(cpu, args):
	value = cpu.get_value(args[0])
	if value != 0:
		cpu.current_instruction += cpu.get_value(args[1])-1

def op_mul(cpu, args):
	value1 = cpu.get_value(args[0])
	value2 = cpu.get_value(args[1])
	cpu.set_register(args[0], value1*value2)

def op_set(cpu, args):
	register = args[0]
	value = cpu.get_value(args[1])
	cpu.set_register(register, value)

def op_sub(cpu, args):
	register = args[0]
	value1 = cpu.get_value(args[0])
	value2 = cpu.get_value(args[1])
	cpu.set_register(register, value1-value2)
