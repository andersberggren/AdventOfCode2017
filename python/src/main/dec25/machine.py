class TuringMachine:
	def __init__(self, rules, initial_state):
		# Dict from position to value
		self.tape = {}
		self.cursor = 0
		# Dict from (state, value_at_cursor) to (value_to_write, move, new_state)
		self.rules = rules
		self.state = initial_state
	
	def step(self, number_of_steps=1):
		for i in range(number_of_steps):  # @UnusedVariable
			(value_to_write, delta_move, new_state) = self.rules[(self.state, self._read())]
			self._write(value_to_write)
			self.cursor += delta_move
			self.state = new_state
	
	def get_diagnostic_checksum(self):
		return len([x for x in self.tape.values() if x == 1])
	
	def _read(self):
		try:
			return self.tape[self.cursor]
		except KeyError:
			return 0
	
	def _write(self, value):
		self.tape[self.cursor] = value
