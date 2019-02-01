from aoclib.filereader import get_file_as_list_of_string
from dec22.network import Network, Node

def get_network_from_file(filename):
	network = Network()
	lines = get_file_as_list_of_string(filename)
	offset = -(len(lines) // 2)
	for y in range(len(lines)):
		line = lines[y]
		for x in range(len(line)):
			location = (x+offset, y+offset)
			symbol = line[x]
			state = _get_state_from_symbol(symbol)
			node = Node(state)
			network.add_node(location, node)
	return network

def _get_state_from_symbol(symbol):
	symbol_to_state = {
		".": Node.State.CLEAN,
		"#": Node.State.INFECTED
	}
	return symbol_to_state[symbol]
