import re

from aoclib.filereader import get_file_as_list_of_string
from dec12.network import Network

def get_network_from_file(filename):
	network = Network()
	for line in get_file_as_list_of_string(filename):
		match = re.match("(\d+) <-> (.*)", line)
		program_from = int(match.group(1))
		for program_to in [int(x.strip()) for x in match.group(2).split(",")]:
			network.add_connection(program_from, program_to)
	return network

if __name__ == "__main__":
	network = get_network_from_file("input12.txt")
	print("Number of programs in the group that contains program ID 0:",
			len(network.get_reachable_programs(0)))
	print("Number of program groups:", network.get_number_of_program_groups())
