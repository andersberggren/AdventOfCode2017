class CPU:
	def __init__(self, shortcuts_enabled=False):
		self.registers = {}
		self.current_instruction = 0
		self.n_mul = 0
		self.instruction_history = []
		self.shortcuts_enabled = shortcuts_enabled
	
	def get_value(self, register_or_value):
		try:
			return int(register_or_value)
		except ValueError:
			pass
		try:
			return self.registers[register_or_value]
		except KeyError:
			pass
		return 0
	
	def set_register(self, register, value):
		self.registers[register] = value
	
	def exec_instructions(self, instructions):
		while True:
			if self.shortcuts_enabled:
				self.find_shortcut()
				self.find_loop()
				self.instruction_history.append(self.current_instruction)
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
	
	def find_loop(self):
		i = len(self.instruction_history)-1
		while i >= 0 and self.instruction_history[i] != self.current_instruction:
			i -= 1
		if i < 0:
			return
		instruction_loop = self.instruction_history[i:]
		loop_size = len(instruction_loop)
		i_loop_start = len(self.instruction_history) - loop_size
		n_loops = 1
		while True:
			i_loop_start -= loop_size
			if i_loop_start < 0:
				break
			is_loop = True
			for i in range(loop_size):
				if self.instruction_history[i+i_loop_start] != instruction_loop[i]:
					is_loop = False
					break
			if not is_loop:
				break
			n_loops += 1
		if n_loops >= 10:
			print(instruction_loop)
			print(self.registers)
			print("Loop size: {s} Number of loops: {n}".format(s=loop_size, n=n_loops))
			print(self.instruction_history)
			raise RuntimeError("Abort")
	
	def find_shortcut(self):
		b = self.get_value("b")
		d = self.get_value("d")
		e = self.get_value("e")
		if self.current_instruction == 11 and e < b-1:
			#print("Shortcut 1!")
			#print("  Registers={}".format(self.get_registers_as_string()))
			if b % d == 0 and e < b // d:
				self.set_register("f", 0)
			for d_iter in range(d+1, b):
				if b % d_iter == 0 and b // d >= 2:
					self.set_register("f", 0)
			self.set_register("d", b-1)
			self.set_register("e", b-1)
			#print("  Registers={}".format(self.get_registers_as_string()))
			self.instruction_history = []
	
	def get_registers_as_string(self):
		sorted_items = sorted(self.registers.items(), key=lambda x: x[0])
		return "[{}]".format(", ".join(["{k}={v}".format(k=k, v=v) for (k,v) in sorted_items]))

def op_jnz(cpu, args):
	if cpu.get_value(args[0]) != 0:
		cpu.current_instruction += cpu.get_value(args[1])-1

def op_mul(cpu, args):
	register = args[0]
	value1 = cpu.get_value(args[0])
	value2 = cpu.get_value(args[1])
	cpu.set_register(register, value1*value2)

def op_set(cpu, args):
	register = args[0]
	value = cpu.get_value(args[1])
	cpu.set_register(register, value)

def op_sub(cpu, args):
	register = args[0]
	value1 = cpu.get_value(args[0])
	value2 = cpu.get_value(args[1])
	cpu.set_register(register, value1-value2)
