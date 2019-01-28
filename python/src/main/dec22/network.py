from enum import Enum, auto

from aoclib.direction import Direction

class Node:
	class State(Enum):
		CLEAN = auto()
		INFECTED = auto()
	
	def __init__(self, state):
		self.state = state

class VirusCarrier:
	def __init__(self):
		self.location = (0, 0)
		self.direction = Direction.up
		self.n_infections_caused = 0
	
	def do_work(self, network):
		node = network.get_node(self.location)
		# Change direction
		state_to_new_direction = {
			Node.State.CLEAN:    Direction.get_direction_to_the_left(self.direction),
			Node.State.INFECTED: Direction.get_direction_to_the_right(self.direction)
		}
		self.direction = state_to_new_direction[node.state]
		# Change node state
		state_to_new_state = {
			Node.State.CLEAN:    Node.State.INFECTED,
			Node.State.INFECTED: Node.State.CLEAN,
		}
		node.state = state_to_new_state[node.state]
		if node.state is Node.State.INFECTED:
			self.n_infections_caused += 1
		# Move
		self.location = Direction.get_new_location(self.location, self.direction)

class Network:
	def __init__(self):
		self.location_to_node = {}
		self.virus_carrier = VirusCarrier()
	
	def add_node(self, location, node):
		self.location_to_node[location] = node
	
	def get_node(self, location):
		try:
			return self.location_to_node[location]
		except KeyError:
			node = Node(Node.State.CLEAN)
			self.location_to_node[location] = node
			return node
	
	def tick(self):
		self.virus_carrier.do_work(self)
