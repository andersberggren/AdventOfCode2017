class TuringMachine:
	def __init__(self):
		# Dict from position to value
		self.tape = {}
		self.cursor = 0
		# Dict from (state, value_at_cursor) to (value_to_write, move, new_state)
		self.rules = {}
		self.state = None
		self.number_of_steps = 0
	
	def add_rule(self, current_state, value_at_cursor, value_to_write, delta_move, new_state):
		self.rules[(current_state, value_at_cursor)] = Action(value_to_write, delta_move, new_state)
	
	def run(self):
		for i in range(self.number_of_steps):  # @UnusedVariable
			action = self.rules[(self.state, self._read())]
			# Write value
			self.tape[self.cursor] = action.value_to_write
			# Move cursor
			self.cursor += action.delta_move
			# Set new state
			self.state = action.new_state
	
	def get_diagnostic_checksum(self):
		return len([x for x in self.tape.values() if x == 1])
	
	def _read(self):
		try:
			return self.tape[self.cursor]
		except KeyError:
			return 0

class Action:
	def __init__(self, value_to_write, delta_move, new_state):
		self.value_to_write = value_to_write
		self.delta_move = delta_move
		self.new_state = new_state
