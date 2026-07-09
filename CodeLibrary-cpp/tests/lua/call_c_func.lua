-- Simple Hello World Lua program
print('Calling C function')
a = testFunction("TESTING", 45, 16, "Car")

io.write('The C function returned: ' .. a .. '\n')
