# BreakProcess

`BreakProcess.exe` is a small helper for debugging simulations on Windows.
GDB's `-exec-interrupt` does not work there, so this tool raises `SIGTRAP` in the  inferior process via `DebugBreakProcess()` instead.
It uses the Win32 debugging API and has no meaning on any other platform.

## Build

```cmd
cmake -S BreakProcess -B BreakProcess/build -DCMAKE_INSTALL_PREFIX=BreakProcess/install
cmake --build BreakProcess/build --target install
```
