class Direction:
	left  = (-1,  0)
	right = ( 1,  0)
	up    = ( 0, -1)
	down  = ( 0,  1)
	all = {left, right, up, down}
	horizontal = {left, right}
	vertical = {up, down}

	@staticmethod
	def get_opposite_direction(direction):
		return (-direction[0], -direction[1])

	@staticmethod
	def get_direction_to_the_left(direction):
		return (direction[1], -direction[0])

	@staticmethod
	def get_direction_to_the_right(direction):
		return (-direction[1], direction[0])

	@staticmethod
	def get_same_direction(direction):
		return direction

	@staticmethod
	def get_new_location(location, direction):
		return (location[0]+direction[0], location[1]+direction[1])
