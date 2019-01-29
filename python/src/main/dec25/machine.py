class TuringMachine:
	def __init__(self, initial_state):
		# Dict from position to value
		self.tape = {}
		self.cursor = 0
		self.state = initial_state
		# Dict from (state, value_at_cursor) to (value_to_write, move, new_state)
		self.rules = {}
	
	def add_rule(self, current_state, value_at_cursor, value_to_write, delta_move, new_state):
		self.rules[(current_state, value_at_cursor)] = (value_to_write, delta_move, new_state)
	
	def read(self):
		try:
			return self.tape[self.cursor]
		except KeyError:
			return 0
	
	def write(self, value):
		self.tape[self.cursor] = value
	
	def move(self, delta_move):
		self.cursor += delta_move
	
	def tick(self):
		(value_to_write, delta_move, new_state) = self.rules[(self.state, self.read())]
		self.write(value_to_write)
		self.move(delta_move)
		self.state = new_state

