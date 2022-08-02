

import asyncio
from bleak import BleakScanner

# import sys
# print("Python version")
# print (sys.version)

async def main():
    devices = await BleakScanner.discover()
    for d in devices:
        print(d)

asyncio.run(main())