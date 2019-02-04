class Network:
	def __init__(self):
		self.program_to_reachable_programs = {}
	
	def add_connection(self, program_id1, program_id2):
		connections1 = self.get_reachable_programs(program_id1)
		connections2 = self.get_reachable_programs(program_id2)
		if connections1 is connections2:
			return
		combined_connections = connections1.union(connections2)
		for program_id in combined_connections:
			self.program_to_reachable_programs[program_id] = combined_connections
	
	def get_reachable_programs(self, program_id):
		try:
			return self.program_to_reachable_programs[program_id]
		except KeyError:
			connected_programs = {program_id}
			self.program_to_reachable_programs[program_id] = connected_programs
			return connected_programs
	
	def get_number_of_program_groups(self):
		program_groups = []
		for program_group in self.program_to_reachable_programs.values():
			if program_group not in program_groups:
				program_groups.append(program_group)
		return len(program_groups)
