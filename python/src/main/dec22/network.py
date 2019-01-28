from enum import Enum, auto

from aoclib.direction import Direction

class Node:
	class State(Enum):
		CLEAN = auto()
		WEAKENED = auto()
		INFECTED = auto()
		FLAGGED = auto()
	
	def __init__(self, state):
		self.state = state

class VirusCarrier:
	def __init__(self, version=1):
		self.location = (0, 0)
		self.direction = Direction.up
		self.state_to_new_direction_function = {
			Node.State.CLEAN:    Direction.get_direction_to_the_left,
			Node.State.WEAKENED: Direction.get_same_direction,
			Node.State.INFECTED: Direction.get_direction_to_the_right,
			Node.State.FLAGGED:  Direction.get_opposite_direction
		}
		self.state_to_new_state = self.get_state_to_new_state(version)
		self.n_infections_caused = 0
	
	def get_state_to_new_state(self, version):
		if version == 1:
			state_cycle = [
				Node.State.CLEAN,
				Node.State.INFECTED
			]
		elif version == 2:
			state_cycle = [
				Node.State.CLEAN,
				Node.State.WEAKENED,
				Node.State.INFECTED,
				Node.State.FLAGGED
			]
		else:
			raise ValueError("Unsupported version:", version)
		state_to_new_state = {}
		for i in range(len(state_cycle)):
			this_state = state_cycle[i]
			next_state = state_cycle[(i+1) % len(state_cycle)]
			state_to_new_state[this_state] = next_state
		return state_to_new_state
	
	def do_work(self, network):
		"""
		The virus carrier does one burst of work, consisting of:
		- Changing direction based on the current node's state.
		- Changing the current node's state.
		- Moving forward, one step.
		"""
		node = network.get_node(self.location)
		self.direction = self.state_to_new_direction_function[node.state](self.direction)
		node.state = self.state_to_new_state[node.state]
		if node.state is Node.State.INFECTED:
			self.n_infections_caused += 1
		self.location = Direction.get_new_location(self.location, self.direction)

class Network:
	def __init__(self):
		self.location_to_node = {}
	
	def add_node(self, location, node):
		self.location_to_node[location] = node
	
	def get_node(self, location):
		try:
			return self.location_to_node[location]
		except KeyError:
			node = Node(Node.State.CLEAN)
			self.location_to_node[location] = node
			return node
