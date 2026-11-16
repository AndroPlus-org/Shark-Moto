package com.shark.kty

enum class AppLanguage(val code: String, val title: String, val nativeName: String) {
    EN("en", "English", "English"),
    ZH("zh", "Chinese", "中文")
}

object AppStrings {
    // Language Selection Gate
    fun langGateTitle(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "选择语言" else "Choose Language"

    fun langGateSubtitle(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "请选择 Shark 应用显示语言\nPlease select your preferred language"
        else "Please select your preferred language\n请选择 Shark 应用显示语言"

    fun langGateContinue(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "继续" else "Continue"

    // Legal Disclaimer
    val legalPhraseZh = "我已确认能接受使用该工具可能造成的相关后果并承诺遵守开源协议"
    val legalPhraseEn = "I accept the risks and promise to comply with the open source license"

    fun legalTitle(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "免责声明与开源协议" else "Disclaimer & Open Source License"

    fun legalIntro(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "使用本工具前，请完整阅读以下内容；确认一次后不再弹出。不认同其中任何一条，请立即退出并卸载。"
        else "Before using this tool, please read the following terms completely. Once confirmed, this prompt will not appear again. If you disagree with any terms, please exit and uninstall immediately."

    fun legalDisclaimerSectionTitle(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "免责声明" else "Disclaimer"

    val legalDisclaimerPointsZh = listOf(
        "1. 本工具仅限用于您本人拥有或已获得明确授权的设备，用于系统研究、学习与日常维护。严禁用于任何未经授权的设备、他人财产或非法用途；由此产生的一切后果由使用者自行承担。",
        "2. 本工具通过利用系统内核漏洞实现临时越狱与提权，属于对操作系统运行时行为的深度修改，其行为方式与常规应用程序有本质区别，可能被杀毒软件、企业 MDM 或应用商店安全扫描标记为风险程序。",
        "3. 使用过程中可能出现设备重启、卡死、无法开机、数据丢失、系统分区损坏等情况；严重时可能需要刷机恢复出厂设置甚至送修。请在操作前完整备份重要数据（照片、聊天记录、密钥、银行与支付凭证等），并确认自己具备恢复设备的能力。",
        "4. 越狱会削弱或绕过系统的部分安全机制（包括 SELinux、签名校验与启动链完整性检查），设备在越狱期间及其后一段时间内更容易受到恶意软件攻击，银行、支付、政务、企业办公等敏感应用可能拒绝运行或检测到环境异常。",
        "5. 越狱后设备的保修与售后政策可能受影响；系统 OTA 更新可能失败、被阻断，或因版本校验不匹配导致设备无法启动。是否保留 Root、何时撤销、何时更新系统，由您自行判断并承担风险。",
        "6. 本工具按「现状」提供，不附带任何明示或默示的担保，包括但不限于适销性、特定用途适用性、稳定运行与不侵权的担保。作者不承诺本工具适用于任何特定设备、系统版本或使用场景。",
        "7. 在适用法律允许的最大范围内，作者与贡献者不对因使用或无法使用本工具造成的任何直接、间接、偶然、特殊或后果性损失承担责任，包括但不限于数据损失、利润损失、业务中断、设备损坏、保修失效与第三方索赔。",
        "8. 您应确保使用行为符合所在地法律法规以及设备所属组织（如雇主、学校）的管理规定；不得利用本工具绕过他人设备的安全机制、规避计费、破解受保护内容、干扰公共网络或从事任何侵权活动。",
        "9. 本工具不提供账户、订阅或云端服务，不收集、不上传您的个人数据，全部操作均在您的设备本地完成。非官方渠道分发的构建版本可能与源码存在差异或含额外改动，请仅从官方仓库获取并自行核对。",
        "10. 是否使用本工具、在哪些设备上使用、使用到何种程度，完全由您自行决定。若您对上述任何风险存在疑虑，或无法承担相应后果，请立即退出并卸载本工具。",
        "11. 本工具包含的第三方组件、预编译库与素材分别遵循其自身许可证，相关声明见应用内「关于 — 许可」页面与随仓库分发的 NOTICE 文件；这些组件不因本声明的签署而改变其许可条件。",
        "12. 作者保留在不另行通知的情况下修改、暂停或终止本项目及其任何功能的权利；条文更新后若您继续使用，即视为接受更新后的全部内容。"
    )

    val legalDisclaimerPointsEn = listOf(
        "1. This tool is strictly intended for devices you personally own or have received explicit authorization to use, for system research, educational purposes, and routine maintenance. Any unauthorized use, use on property of others, or for unlawful purposes is strictly prohibited; all consequences arising therefrom shall be borne solely by the user.",
        "2. This tool exploits operating system kernel vulnerabilities to achieve temporary jailbreak and root privilege escalation. It deeply modifies OS runtime behavior, fundamentally differing from standard applications, and may be flagged as high-risk by antivirus software, enterprise MDM, or security scanners.",
        "3. During operation, device reboots, freezes, bootloops, data loss, or partition corruption may occur; in severe cases, flashing stock firmware or hardware servicing may be required. Fully back up all important data (photos, chats, keys, credentials) before proceeding and ensure you can recover your device.",
        "4. Jailbreaking weakens or bypasses system security mechanisms (SELinux, signature verification, verified boot). The device becomes more vulnerable to malware, and sensitive applications (banking, payment, enterprise apps) may refuse to run or detect an abnormal environment.",
        "5. Device warranty and after-sales service policies may be affected. System OTA updates may fail, be blocked, or cause boot failures due to version mismatches. Whether to retain root, when to revoke it, and when to update firmware is solely at your own discretion and risk.",
        "6. This tool is provided \"AS IS\" without warranty of any kind, express or implied, including but not limited to warranties of merchantability, fitness for a particular purpose, stability, and non-infringement. Authors do not warrant suitability for any specific device, OS build, or use case.",
        "7. To the maximum extent permitted by applicable law, the authors and contributors disclaim liability for any direct, indirect, incidental, special, or consequential damages, including loss of data, profit loss, business disruption, device damage, warranty invalidation, or third-party claims.",
        "8. You must ensure that your usage complies with applicable local laws and regulations as well as policies of organizations governing your device. You must not use this tool to bypass security controls on others' devices, avoid billing, crack protected content, or disrupt networks.",
        "9. This tool does not provide accounts, subscriptions, or cloud services; it does not collect or upload personal data. All operations execute strictly locally on your device. Unofficial builds may contain modifications; obtain builds only from official repositories and verify checksums.",
        "10. Whether to use this tool, on which devices, and to what extent is entirely your choice. If you have any doubts regarding the risks or cannot accept the potential consequences, please exit and uninstall this tool immediately.",
        "11. Third-party components, precompiled libraries, and assets bundled with this tool are subject to their respective licenses (see \"About — Licenses\" and the NOTICE file). Their licensing terms are not altered by this agreement.",
        "12. The authors reserve the right to modify, suspend, or terminate this project or any features without prior notice. Continued use following any revisions constitutes full acceptance of the updated terms."
    )

    fun legalLicenseSectionTitle(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "开源许可（GNU GPLv3）" else "Open Source License (GNU GPLv3)"

    val legalLicensePointsZh = listOf(
        "1. 本项目（含应用程序与内核载荷源码）以 GNU 通用公共许可证第 3 版（GPLv3）发布，许可证全文见项目根目录的 LICENSE 文件，二者内容不一致时以 LICENSE 全文为准。",
        "2. 您享有运行、研究、修改与再分发本程序的自由，但行使上述自由的前提是完整遵守 GPLv3 的全部条款。",
        "3. 再分发本程序或其修改版本时，必须保留原有的版权声明与许可证文本；在修改过的文件中显著标明修改内容及修改日期；并向接收者提供完整的对应源代码（§5）。",
        "4. 以目标代码（二进制）形式分发时，必须同时以不高于实际分发成本的合理方式提供对应源代码，或提供有效期不少于三年的书面获取承诺（§6）。",
        "5. 通过用户产品（如预装设备、定制固件）分发时，还须提供必要的「安装信息」，使接收者能够在其设备上安装修改后的版本（§6，防止通过硬件限制架空许可证）。",
        "6. 您不得对 GPLv3 授予的权利附加任何额外限制；分发时不得附加与本许可证冲突的条款，亦不得对接收者行使许可证权利收取许可费（§7、§10）。",
        "7. 本程序的贡献者授予您非排他性的专利许可；若您对任何贡献者发起专利诉讼，您依据本许可证获得的专利许可将相应终止（§11）。",
        "8. 许可证授权在您违反条款时自动终止；若您在收到通知后 30 天内纠正违规行为，授权可自纠正之日起恢复（§8）。",
        "9. 本程序不提供任何担保；在适用法律允许的范围内，作者与贡献者不对任何损害承担赔偿责任（§15、§16）。",
        "10. GPLv3 仅授予著作权与专利层面的许可，不授予本项目名称、图标、商标或品牌的使用权；未经许可不得以本项目名义发布衍生版本或暗示官方背书。",
        "11. 违反本许可证的，授权将按 §8 终止；在授权终止后继续复制、分发或修改本程序的，可能构成著作权侵权并承担相应法律责任。"
    )

    val legalLicensePointsEn = listOf(
        "1. This project (including app and kernel payload source code) is released under the GNU General Public License version 3 (GPLv3). The full license text is located in the LICENSE file at the repository root; in case of discrepancy, the LICENSE file prevails.",
        "2. You have the freedom to run, study, modify, and redistribute this program, conditioned upon full compliance with all provisions of GPLv3.",
        "3. When redistributing this program or modified versions, you must preserve original copyright and license notices, prominently mark modified files with change notices and dates, and provide corresponding complete source code to recipients (§5).",
        "4. When distributing in object code (binary) form, you must accompany it with corresponding source code via a reasonable medium at no more than actual distribution cost, or provide a written offer valid for at least three years (§6).",
        "5. When distributing as part of a user product (e.g. pre-installed devices, custom firmware), you must provide necessary Installation Information enabling recipients to install modified versions on their device (§6).",
        "6. You may not impose any further restrictions on the rights granted under GPLv3, attach contradictory terms, or charge licensing fees for the exercise of rights under this license (§7, §10).",
        "7. Contributors grant you a non-exclusive patent license; if you initiate patent litigation against any contributor, your patent license under this agreement terminates accordingly (§11).",
        "8. License authorization terminates automatically upon breach of terms; if you rectify the violation within 30 days of notice, authorization can be reinstated from the date of cure (§8).",
        "9. This program is provided without warranty; to the extent permitted by law, authors and contributors disclaim liability for any damages (§15, §16).",
        "10. GPLv3 grants copyright and patent permissions only; it does not grant rights to project names, logos, trademarks, or branding. Derivative builds may not imply official endorsement.",
        "11. Any violation terminates authorization pursuant to §8. Continuing to copy, distribute, or modify after termination constitutes copyright infringement subject to legal liability."
    )

    fun legalEnd(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "— 以上为全部条文 —" else "— End of Terms —"

    fun legalScrollPrompt(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "请滑动上方条文到最底部，并原样输入以下文字："
        else "Please scroll to the very bottom and enter the following text exactly:"

    fun legalTapToFill(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "（可点击上方文字自动填入）" else "(Tap text above to auto-fill)"

    fun legalPlaceholder(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "在此输入确认文字" else "Enter confirmation text here"

    fun legalConfirmButton(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "确认并进入" else "Confirm & Proceed"

    fun legalMustScroll(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "请先滑动到条文最底部" else "Please scroll to the very bottom first"

    fun legalTextMismatch(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "输入内容与要求不一致，请原样输入" else "Input does not match the required phrase"

    // Navigation Tabs
    fun tabHome(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "首页" else "Home"
    fun tabLogs(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "日志" else "Logs"
    fun tabHistory(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "历史" else "History"
    fun tabAbout(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "关于" else "About"

    // Top Bar
    fun titleExtras(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "拓展功能" else "Extras"
    fun titleManual(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "使用手册" else "Manual"
    fun titleLicenses(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "开放源代码许可" else "Open Source Licenses"

    // Home Screen
    fun statusAuthorized(backend: String, lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "$backend 已授权" else "$backend Authorized"
    fun statusNotAuthorized(backend: String, lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "$backend 未授权" else "$backend Not Authorized"
    fun statusReadyDesc(backend: String, lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "已连接到 $backend 服务，可以开始越狱" else "Connected to $backend, ready to root"
    fun statusNotReadyDesc(backend: String, lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "未连接到 $backend 服务，请点击授权" else "Not connected to $backend, please grant permission"

    fun actionRunning(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "执行中…" else "Running…"
    fun actionStellarNotRunning(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "Stellar 未运行（点击打开）" else "Stellar not running (tap to open)"
    fun actionAuthorizeShizuku(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "授权 Shizuku" else "Authorize Shizuku"
    fun actionReRoot(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "检查 / 重新越狱" else "Verify / Re-root"
    fun actionOneClickRoot(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "一键越狱" else "One-Click Root"

    fun rootManager(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "Root 方案" else "Root Manager"
    fun extras(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "拓展功能" else "Extras"
    fun manual(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "使用手册" else "Manual"
    fun manualSummary(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "使用步骤、功能说明与排障" else "Setup, features, and troubleshooting"

    fun kernelVersion(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "内核版本" else "Kernel Version"
    fun exploitMethod(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "利用方案" else "Exploit Method"
    fun deviceModel(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "设备机型" else "Device Model"
    fun buildFingerprint(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "构建指纹" else "Build Fingerprint"
    fun selinuxStatus(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "SELinux 状态" else "SELinux Status"

    fun unsupportedDevice(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "当前机型不支持" else "Unsupported Device"

    // About Screen
    fun appLanguage(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "应用语言" else "App Language"
    fun chooseAppLanguage(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "选择应用语言" else "Choose App Language"
    fun aboutIconNotice(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "App 图标由本项目绘制；第三方组件版权归各自作者所有，完整许可见「开放源代码许可」。"
        else "The app icon was designed for this project; third-party components belong to their respective authors. Full texts are available in Open Source Licenses."

    fun sourceCode(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "查看源代码" else "Source Code"
    fun sourceCodeSummary(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "在 GitHub 上查看源代码" else "View source code on GitHub"
    fun community(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "加入社区" else "Community"
    fun communitySummary(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "加入 QQ 群" else "Join QQ Group"
    fun reportIssues(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "问题反馈" else "Report Issues"
    fun reportIssuesSummary(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "在 GitHub Issues 提交问题" else "Submit issues on GitHub Issues"
    fun checkForUpdates(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "检查更新" else "Check for Updates"
    fun currentVersionPrefix(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "当前 v" else "Current v"
    fun supportDeveloper(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "支持开发者" else "Support the Developer"
    fun supportDeveloperSummary(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "微信 / 支付宝赞赏二维码" else "WeChat / Alipay donation QR codes"
    fun openSourceLicenses(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "开放源代码许可" else "Open Source Licenses"
    fun openSourceLicensesSummary(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "查看本应用所使用的第三方开源库及其许可证信息" else "View third-party open source libraries and license details"

    fun projectAndBundled(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "本项目与附加组件" else "Project & Bundled Components"
    fun thirdPartyDeps(count: Int, lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) {
            if (count == 0) "第三方依赖" else "第三方依赖（$count）"
        } else {
            if (count == 0) "Third-Party Dependencies" else "Third-Party Dependencies ($count)"
        }

    // Extras Screen
    fun disableSecurityAlerts(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "越狱后停用安全警告" else "Disable security alerts after root"
    fun softRebootAfterRoot(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "越狱后软重启" else "Soft reboot after root"
    fun softRebootDelay(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "软重启延迟时间" else "Soft reboot delay"
    fun unloadKernelGuard(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "自动卸载内核安全防护" else "Unload kernel security guard"
    fun chaseRetry(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "打偏后追页重试" else "Chase retry on miss"
    fun autoJailbreak(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "开机自动越狱" else "Auto jailbreak on boot"
    fun susfsSupport(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "SUSFS 隐藏支持" else "SUSFS Support"
    fun vfsSupport(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "VFS 虚拟文件系统" else "VFS Support"
    fun hotSwitchManager(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "热切换 Root 管理器" else "Hot-Switch Manager"
    fun spoofedManager(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "伪装版管理器包名" else "Spoofed Manager Package"
    fun spoofedManagerScan(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "扫描并自动检测" else "Scan & Auto-Detect"
    fun spoofedManagerDetected(pkg: String, lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "已检测到伪装管理器：$pkg" else "Detected spoofed manager: $pkg"
    fun spoofedManagerNotFound(lang: AppLanguage): String =
        if (lang == AppLanguage.ZH) "未检测到已安装的伪装版管理器" else "No spoofed manager detected"

    // Update Dialog
    fun updateAvailable(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "发现新版本" else "Update Available"
    fun currentVersionLabel(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "当前版本" else "Current version"
    fun latestVersionLabel(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "最新版本" else "Latest version"
    fun updateNow(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "立即更新" else "Update Now"
    fun downloadBrowser(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "浏览器下载" else "Browser Download"
    fun cancel(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "取消" else "Cancel"
    fun close(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "关闭" else "Close"
    fun retry(lang: AppLanguage): String = if (lang == AppLanguage.ZH) "重试" else "Retry"
}
