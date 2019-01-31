import re

from aoclib.filereader import get_file_as_list_of_string
from dec25.machine import TuringMachine

def get_turing_machine_from_file(filename):
	"""
	Returns a TuringMachine.
	"""
	patterns_and_handler_functions = [
		("Begin in state (.)",                             _set_initial_state),
		("Perform a diagnostic checksum after (\d+) step", _set_number_of_steps),
		("In state (.):",                                  _add_rule)
	]
	tm = TuringMachine()
	lines = get_file_as_list_of_string(filename)
	while lines:
		line = lines.pop(0)
		for (pattern, handler_function) in patterns_and_handler_functions:
			match = re.match(pattern, line)
			if match:
				handler_function(tm, lines, match)
				break
	return tm

def _set_initial_state(tm, lines, match):  # @UnusedVariable
	tm.state = match.group(1)

def _set_number_of_steps(tm, lines, match):  # @UnusedVariable
	tm.number_of_steps = int(match.group(1))

def _add_rule(tm, lines, match):
	direction_string_to_int = {"left": -1, "right": 1}
	current_state = match.group(1)
	while lines and lines[0].strip() != "":
		value_at_cursor = int(re.match(" *If the current value is ([01]):", lines.pop(0)).group(1))
		value_to_write = int(re.match(" *- Write the value ([01])", lines.pop(0)).group(1))
		move_string = re.match(" *- Move one slot to the (.*)\.", lines.pop(0)).group(1)
		move_int = direction_string_to_int[move_string]
		new_state = re.match(" *- Continue with state (.)", lines.pop(0)).group(1)
		tm.add_rule(current_state, value_at_cursor, value_to_write, move_int, new_state)
