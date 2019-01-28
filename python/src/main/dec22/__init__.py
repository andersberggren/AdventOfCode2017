from dec22.parser import get_network_from_file
from dec22.network import VirusCarrier

#############
# Functions #
#############
def run_simulation(network, virus_carrier_version, n_iterations):
	virus_carrier = VirusCarrier(virus_carrier_version)
	for i in range(n_iterations):  # @UnusedVariable
		virus_carrier.do_work(network)
	print("Virus carrier (version {v}) caused {inf} infections after {iter} iterations".format(
			v=virus_carrier_version, inf=virus_carrier.n_infections_caused, iter=n_iterations))

########
# Main #
########
if __name__ == "__main__":
	network = get_network_from_file("input22.txt")
	run_simulation(network, 1, 10000)
	network = get_network_from_file("input22.txt")
	run_simulation(network, 2, 10000000)
