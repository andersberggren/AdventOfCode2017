def get_file_as_string(filename):
	with open(filename) as f:
		return f.read()

def get_file_as_list_of_string(filename):
	return get_file_as_string(filename).splitlines()
