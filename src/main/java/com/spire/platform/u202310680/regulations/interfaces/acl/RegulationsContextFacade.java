package com.spire.platform.u202310680.regulations.interfaces.acl;

public interface RegulationsContextFacade {
    /**
     * Fetches the max safe duration for a given orbit class.
     * @param orbitClass The orbit class identifier (e.g. "LEO")
     * @return The max safe duration in minutes, or 0 if not found.
     */
    Integer fetchMaxSafeDurationByOrbitClass(String orbitClass);
}
