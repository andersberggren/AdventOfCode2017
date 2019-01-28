from dec22.parser import get_network_from_file

########
# Main #
########
if __name__ == "__main__":
	network = get_network_from_file("input22.txt")
	n_iterations = 10000
	for i in range(n_iterations):
		network.tick()
	print("Number of infections caused after {iter} iterations: {inf}".format(
			iter=n_iterations, inf=network.virus_carrier.n_infections_caused))
