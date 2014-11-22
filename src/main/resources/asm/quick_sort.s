	.file	"quick_sort.cpp"
	.text
	.def	__tcf_0;	.scl	3;	.type	32;	.endef
	.seh_proc	__tcf_0
__tcf_0:
.LFB1095:
	subq	$40, %rsp
	.seh_stackalloc	40
	.seh_endprologue
	leaq	_ZStL8__ioinit(%rip), %rcx
	call	_ZNSt8ios_base4InitD1Ev
	nop
	addq	$40, %rsp
	ret
	.seh_endproc
	.globl	_Z9fast_sortPiii
	.def	_Z9fast_sortPiii;	.scl	2;	.type	32;	.endef
	.seh_proc	_Z9fast_sortPiii
_Z9fast_sortPiii:
.LFB1085:
	pushq	%rdi
	.seh_pushreg	%rdi
	pushq	%rsi
	.seh_pushreg	%rsi
	pushq	%rbx
	.seh_pushreg	%rbx
	subq	$32, %rsp
	.seh_stackalloc	32
	.seh_endprologue
	movq	%rcx, %rsi
	movl	%r8d, %edi
	movslq	%edx, %rax
	movl	(%rcx,%rax,4), %r11d
	leal	1(%rdx), %eax
	cmpl	%r8d, %eax
	jg	.L10
	movl	%edx, %ebx
	movl	%r8d, %r9d
	movl	$0, %ecx
.L8:
	testb	%cl, %cl
	je	.L4
	movslq	%eax, %r8
	movl	(%rsi,%r8,4), %r8d
	cmpl	%r8d, %r11d
	jle	.L5
	movslq	%ebx, %rbx
	movl	%r8d, (%rsi,%rbx,4)
	movl	%eax, %ebx
	movl	$0, %ecx
.L5:
	addl	$1, %eax
	jmp	.L6
.L4:
	movslq	%r9d, %r8
	movl	(%rsi,%r8,4), %r10d
	cmpl	%r10d, %r11d
	jge	.L7
	movslq	%ebx, %rbx
	movl	%r10d, (%rsi,%rbx,4)
	movl	%r9d, %ebx
	movl	$1, %ecx
.L7:
	subl	$1, %r9d
.L6:
	cmpl	%eax, %r9d
	jge	.L8
	movslq	%ebx, %rax
	movl	%r11d, (%rsi,%rax,4)
	movl	%ebx, %eax
	subl	%edx, %eax
	cmpl	$1, %eax
	jle	.L3
	leal	-1(%rbx), %r8d
	movq	%rsi, %rcx
	call	_Z9fast_sortPiii
	jmp	.L3
.L10:
	movl	%edx, %ebx
.L3:
	movl	%edi, %eax
	subl	%ebx, %eax
	cmpl	$1, %eax
	jle	.L2
	leal	1(%rbx), %edx
	movl	%edi, %r8d
	movq	%rsi, %rcx
	call	_Z9fast_sortPiii
	nop
.L2:
	addq	$32, %rsp
	popq	%rbx
	popq	%rsi
	popq	%rdi
	ret
	.seh_endproc
	.def	__main;	.scl	2;	.type	32;	.endef
	.section .rdata,"dr"
.LC0:
	.ascii "  \0"
	.text
	.globl	main
	.def	main;	.scl	2;	.type	32;	.endef
	.seh_proc	main
main:
.LFB1084:
	pushq	%rbp
	.seh_pushreg	%rbp
	pushq	%rdi
	.seh_pushreg	%rdi
	pushq	%rsi
	.seh_pushreg	%rsi
	pushq	%rbx
	.seh_pushreg	%rbx
	subq	$88, %rsp
	.seh_stackalloc	88
	.seh_endprologue
	call	__main
	movl	$11, 32(%rsp)
	movl	$25, 36(%rsp)
	movl	$11, 40(%rsp)
	movl	$4, 44(%rsp)
	movl	$88, 48(%rsp)
	movl	$2, 52(%rsp)
	movl	$108, 56(%rsp)
	movl	$3, 60(%rsp)
	movl	$2, 64(%rsp)
	movl	$21, 68(%rsp)
	leaq	32(%rsp), %rsi
	movl	$9, %r8d
	movl	$0, %edx
	movq	%rsi, %rcx
	call	_Z9fast_sortPiii
	movq	%rsi, %rbx
	addq	$40, %rsi
	movq	.refptr._ZSt4cout(%rip), %rbp
	leaq	.LC0(%rip), %rdi
.L13:
	movl	(%rbx), %edx
	movq	%rbp, %rcx
	call	_ZNSolsEi
	movl	$2, %r8d
	movq	%rdi, %rdx
	movq	%rax, %rcx
	call	_ZSt16__ostream_insertIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_PKS3_x
	addq	$4, %rbx
	cmpq	%rsi, %rbx
	jne	.L13
	movq	.refptr._ZSt4cout(%rip), %rcx
	call	_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_
	movl	$0, %eax
	addq	$88, %rsp
	popq	%rbx
	popq	%rsi
	popq	%rdi
	popq	%rbp
	ret
	.seh_endproc
	.def	_GLOBAL__sub_I_main;	.scl	3;	.type	32;	.endef
	.seh_proc	_GLOBAL__sub_I_main
_GLOBAL__sub_I_main:
.LFB1096:
	subq	$40, %rsp
	.seh_stackalloc	40
	.seh_endprologue
	leaq	_ZStL8__ioinit(%rip), %rcx
	call	_ZNSt8ios_base4InitC1Ev
	leaq	__tcf_0(%rip), %rcx
	call	atexit
	nop
	addq	$40, %rsp
	ret
	.seh_endproc
	.section	.ctors,"w"
	.align 8
	.quad	_GLOBAL__sub_I_main
.lcomm _ZStL8__ioinit,1,1
	.ident	"GCC: (x86_64-posix-seh-rev0, Built by MinGW-W64 project) 4.9.1"
	.def	_ZNSt8ios_base4InitD1Ev;	.scl	2;	.type	32;	.endef
	.def	_ZNSolsEi;	.scl	2;	.type	32;	.endef
	.def	_ZSt16__ostream_insertIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_PKS3_x;	.scl	2;	.type	32;	.endef
	.def	_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_;	.scl	2;	.type	32;	.endef
	.def	_ZNSt8ios_base4InitC1Ev;	.scl	2;	.type	32;	.endef
	.def	atexit;	.scl	2;	.type	32;	.endef
	.section	.rdata$.refptr._ZSt4cout, "dr"
	.globl	.refptr._ZSt4cout
	.linkonce	discard
.refptr._ZSt4cout:
	.quad	_ZSt4cout
