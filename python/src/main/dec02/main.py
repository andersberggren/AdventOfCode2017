from aoclib.filereader import get_file_as_list_of_string

def get_value_of_row_v1(row):
	return max(row) - min(row)

def get_value_of_row_v2(row):
	return next(x1 // x2 for x1 in row for x2 in row if x1 != x2 and x1 % x2 == 0)

rows = [[int(x) for x in row.split()] for row in get_file_as_list_of_string("input02.txt")]
for row_value_function in [get_value_of_row_v1, get_value_of_row_v2]:
	print("Checksum:", sum(row_value_function(row) for row in rows))
