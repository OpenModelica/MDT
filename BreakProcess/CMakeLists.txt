cmake_minimum_required(VERSION 3.14)
project(BreakProcess CXX)

if(NOT WIN32)
  message(FATAL_ERROR "BreakProcess.exe is a small helper for debugging simulations on Windows."
                      " Only applicable if target system is Windows.")
endif()

add_executable(BreakProcess src/BreakProcess.cpp)

if(CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
  # Keep the tool runnable without the MinGW runtime DLLs next to it, the way
  # the old 'make breakprocess' target built it.
  target_link_options(BreakProcess PRIVATE -static-libgcc -static-libstdc++)
endif()

install(TARGETS BreakProcess
        RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
        COMPONENT omc)
